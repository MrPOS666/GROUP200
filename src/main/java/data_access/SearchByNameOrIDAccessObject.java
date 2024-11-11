package data_access;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Cocktail;
import entity.CocktailFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.search.SearchDataAccessInterface;

/**
 * DAO of SearchByNameOrID.
 */
public class SearchByNameOrIDAccessObject implements SearchDataAccessInterface {

    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1";
    private final OkHttpClient client = new OkHttpClient();
    private String currentCocktailName;

    @Override
    public boolean existsByName(String cocktailName) {
        return getByName(cocktailName) != null;
    }

    @Override
    public boolean existsById(int cocktailId) {
        return getById(cocktailId) != null;
    }

    @Override
    public Cocktail getByName(String cocktailName) {
        String jsonResponse = searchByName(cocktailName);
        return createCocktailFromJson(jsonResponse);
    }

    @Override
    public Cocktail getById(int cocktailId) {
        String jsonResponse = searchByID(String.valueOf(cocktailId));
        return createCocktailFromJson(jsonResponse);
    }

    @Override
    public String getCurrentCocktailName() {
        return currentCocktailName;
    }

    @Override
    public void setCurrentCocktailName(String cocktailName) {
        this.currentCocktailName = cocktailName;
    }

    // Factory method to create a Cocktail object from JSON response
    private Cocktail createCocktailFromJson(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }

        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray drinksArray = jsonObject.optJSONArray("drinks");

        if (drinksArray != null && drinksArray.length() > 0) {
            JSONObject drinkObject = drinksArray.getJSONObject(0);

            // Extract main fields
            int idDrink = drinkObject.optInt("idDrink");
            String strDrink = drinkObject.optString("strDrink");
            String strInstructions = drinkObject.optString("strInstructions");
            String photoUrl = drinkObject.optString("strDrinkThumb");

            // Extract ingredients and measurements into a Map
            Map<String, String> ingredients = new HashMap<>();
            // Loop through possible ingredient and measure fields
            for (int i = 1; i <= 15; i++) {
                String ingredient = drinkObject.optString("strIngredient" + i);
                String measure = drinkObject.optString("strMeasure" + i);

                // Add only non-empty ingredients to the map
                if (ingredient != null && !ingredient.isEmpty()) {
                    // Add ingredient as key, measure as value
                    ingredients.put(ingredient, measure != null ? measure : "");
                }
                else {
                    break;
                }
            }

            // Create and return Cocktail object
            return new CocktailFactory().create(idDrink, strDrink, strInstructions, photoUrl, ingredients);
        }

        return null;
    }

    @Override
    public String searchByName(String name) {
        final String url = BASE_URL + "/search.php?s=" + name;
        return makeApiCall(url);
    }

    @Override
    public String searchByID(String id) {
        final String url = BASE_URL + "/lookup.php?i=" + id;
        return makeApiCall(url);
    }

    // Helper method to make an API call and return the response as a string
    private String makeApiCall(String url) {
        final Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
            else {
                System.err.println("Request failed with code: " + response.code());
                return null;
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
