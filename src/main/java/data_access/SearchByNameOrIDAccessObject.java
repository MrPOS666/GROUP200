package data_access;

import java.io.IOException;

import entity.CommonCocktail;
import org.json.JSONArray;
import org.json.JSONObject;

import entity.Cocktail;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import use_case.search.SearchDataAccessInterface;

/**
 * The DAO for Cocktail Data.
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
        final String jsonResponse = searchByName(cocktailName);
        return parseCocktailFromResponse(jsonResponse);
    }

    @Override
    public Cocktail getById(int cocktailId) {
        final String jsonResponse = searchByID(String.valueOf(cocktailId));
        return parseCocktailFromResponse(jsonResponse);
    }

    @Override
    public String getCurrentCocktailName() {
        return currentCocktailName;
    }

    @Override
    public void setCurrentCocktailName(String cocktailName) {
        this.currentCocktailName = cocktailName;
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

    // Helper method to parse JSON response into a Cocktail object
    private Cocktail parseCocktailFromResponse(String jsonResponse) {
        if (jsonResponse == null) {
            return null;
        }

        final JSONObject jsonObject = new JSONObject(jsonResponse);
        final JSONArray drinksArray = jsonObject.optJSONArray("drinks");

        if (drinksArray != null && drinksArray.length() > 0) {
            final JSONObject drinkObject = drinksArray.getJSONObject(0);
            final Cocktail cocktail = new CommonCocktail(
                    drinkObject.optInt("idDrink"),
                    drinkObject.optString("strDrink"),
                    drinkObject.optString("strImage")
                    drinkObject.optString("strInstructions"));

            return cocktail;
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
}
