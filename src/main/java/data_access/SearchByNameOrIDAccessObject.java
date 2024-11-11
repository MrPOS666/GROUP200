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

    public static final int MAXINGREDIENT = 15;
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1";
    private final OkHttpClient client = new OkHttpClient();
    private String currentCocktailName;
    private final CocktailFactory cocktailFactory;

    public SearchByNameOrIDAccessObject(CocktailFactory cocktailFactory) {
        this.cocktailFactory = cocktailFactory;
    }

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
        final String jsonResponse = searchByName(cocktailName);
        return createCocktailFromJson(jsonResponse);
    }

    @Override
    public Cocktail getById(int cocktailId) {
        final String jsonResponse = searchByID(String.valueOf(cocktailId));
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

        final JSONObject jsonObject = new JSONObject(jsonResponse);
        final JSONArray drinksArray = jsonObject.optJSONArray("drinks");

        if (drinksArray != null && drinksArray.length() > 0) {
            final JSONObject drinkObject = drinksArray.getJSONObject(0);

            // Extract main fields
            final int idDrink = drinkObject.optInt("idDrink");
            final String strDrink = drinkObject.optString("strDrink");
            final String strInstructions = drinkObject.optString("strInstructions");
            final String photoUrl = drinkObject.optString("strDrinkThumb");

            // Extract ingredients and measurements into a Map
            final Map<String, String> ingredients = new HashMap<>();
            // Loop through possible ingredient and measure fields
            for (int i = 1; i <= MAXINGREDIENT; i++) {
                final String ingredient = drinkObject.optString("strIngredient" + i);
                final String measure = drinkObject.optString("strMeasure" + i);

                // Add only non-empty ingredients to the map
                if (ingredient != null && !ingredient.isEmpty()) {
                    // Add ingredient as key, measure as value
                    if (measure != null && !measure.isEmpty()) {
                        ingredients.put(ingredient, measure);
                    }
                    else {
                        ingredients.put(ingredient, "");
                    }
                }
                else {
                    break;
                }
            }

            // Create and return Cocktail object
            return cocktailFactory.create(idDrink, strDrink, strInstructions, photoUrl, ingredients);
        }

        return null;
    }

    private String searchByName(String name) {
        final String url = BASE_URL + "/search.php?s=" + name;
        return makeApiCall(url);
    }

    private String searchByID(String id) {
        final String url = BASE_URL + "/lookup.php?i=" + id;
        return makeApiCall(url);
    }

    // Helper method to make an API call and return the response as a string
    private String makeApiCall(String url) {
        final Request request = new Request.Builder()
                .url(url)
                .build();

        String result = null;
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                result = response.body().string();
            }
            else {
                System.err.println("Request failed with code: " + response.code());
            }
        }
        catch (IOException exception) {
            exception.printStackTrace();
        }
        return result;
    }
}
