package data_access;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;

import entity.Cocktail;
import entity.CocktailFactory;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.search.SearchDataAccessInterface;
import use_case.search_by_ingredients.IngredientsDataAccessInterface;

/**
 * DAO of SearchByNameOrID.
 */
public class SearchByNameOrIDAccessObject implements SearchDataAccessInterface, IngredientsDataAccessInterface {

    public static final int MAX_INGREDIENT = 15;
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1";
    private final OkHttpClient client = new OkHttpClient();
    private String currentCocktailName;
    private final CocktailFactory cocktailFactory;

    public SearchByNameOrIDAccessObject(CocktailFactory cocktailFactory) {
        this.cocktailFactory = cocktailFactory;
    }

    @Override
    public boolean existsByName(String cocktailName) {
        return !getByName(cocktailName).isEmpty();
    }

    @Override
    public boolean existsById(int cocktailId) {
        return getById(cocktailId) != null;
    }

    @Override
    public List<Cocktail> getByName(String cocktailName) {
        final String jsonResponse = searchByName(cocktailName);
        return createCocktailsFromJson(jsonResponse);
    }

    @Override
    public Cocktail getById(int cocktailId) {
        final String jsonResponse = searchByID(String.valueOf(cocktailId));
        final List<Cocktail> cocktails = createCocktailsFromJson(jsonResponse);
        Cocktail result = null;
        if (!cocktails.isEmpty()) {
            result = cocktails.get(0);
        }
        return result;
    }

    @Override
    public boolean existsByIngredients(List<String> ingredients) {
        return !getByIngredients(ingredients).isEmpty();
    }

    @Override
    public String getCurrentCocktailName() {
        return currentCocktailName;
    }

    @Override
    public List<Cocktail> getByIngredients(List<String> ingredients) {
        final Set<Cocktail> cocktails = new HashSet<Cocktail>();
        for (String i: ingredients) {
            final String jsonResponse = searchByIngredient(i);
            cocktails.addAll(createCocktailsFromJson(jsonResponse));
        }
        final List<Cocktail> cocktailList = new ArrayList<>();
        cocktailList.addAll(cocktails);
        return cocktailList;
    }

    /**
     * Helper method to get full cocktail information from id
     * @param jsonResponse
     * @return
     */
    public List<Cocktail> cocktailsFromJson(String jsonResponse) {
        final Set<Cocktail> cocktails = new HashSet<>();
        final List<Cocktail> cocktailList = new ArrayList<>();
        cocktailList.addAll(cocktails);
        if (jsonResponse == null) {
            return cocktailList;
        }

        final JSONObject jsonObject = new JSONObject(jsonResponse);
        final JSONArray drinksArray = jsonObject.optJSONArray("drinks");

        if (drinksArray != null) {
            for (int i = 0; i < drinksArray.length(); i++) {
                final JSONObject drinkObject = drinksArray.getJSONObject(i);
                final int idDrink = drinkObject.optInt("idDrink");
                cocktails.add(getById(idDrink));
            }
        }
        cocktailList.addAll(cocktails);
        return cocktailList;
    }

    @Override
    public void setCurrentCocktailName(String cocktailName) {
        this.currentCocktailName = cocktailName;
    }

    // Factory method to create a list of Cocktail objects from JSON response
    private List<Cocktail> createCocktailsFromJson(String jsonResponse) {
        final List<Cocktail> cocktails = new ArrayList<>();

        if (jsonResponse == null) {
            return cocktails;
        }

        final JSONObject jsonObject = new JSONObject(jsonResponse);
        final JSONArray drinksArray = jsonObject.optJSONArray("drinks");

        if (drinksArray != null) {
            for (int i = 0; i < drinksArray.length(); i++) {
                final JSONObject drinkObject = drinksArray.getJSONObject(i);

                // Extract main fields
                final int idDrink = drinkObject.optInt("idDrink");
                final String strDrink = drinkObject.optString("strDrink");
                final String strInstructions = drinkObject.optString("strInstructions");
                final String photoUrl = drinkObject.optString("strDrinkThumb");

                // Extract ingredients and measurements into a Map
                final Map<String, String> ingredients = new HashMap<>();
                for (int j = 1; j <= MAX_INGREDIENT; j++) {
                    final String ingredient = drinkObject.optString("strIngredient" + j);
                    final String measure = drinkObject.optString("strMeasure" + j);

                    // Add only non-empty ingredients to the map
                    if (ingredient != null && !ingredient.isEmpty()) {
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

                // Create a Cocktail object and add it to the list
                final Cocktail cocktail = cocktailFactory.create(idDrink,
                        strDrink, strInstructions, photoUrl, ingredients);
                cocktails.add(cocktail);
            }
        }

        return cocktails;
    }

    private String searchByIngredient(String ingredient) {
        final String url = BASE_URL + "/filter.php?i=" + ingredient;
        return makeApiCall(url);
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
