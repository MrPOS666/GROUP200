package data_access;

import java.io.IOException;
import java.util.*;

import entity.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import use_case.delete_favorite.DeleteDataAccessInterface;
import use_case.detailPage.DetailPageDataAccessException;

/**
 * The DAO for accessing users and their associated cocktails stored in the database.
 * <p>This class demonstrates how your group can use the password-protected user
 * endpoints of the API to store persistent data about users and their cocktails.</p>
 */
public class DBUserDataAccessObject2 implements DeleteDataAccessInterface {

    private static final int SUCCESS_CODE = 200;
    private static final int CREDENTIAL_ERROR = 401;
    private static final String CONTENT_TYPE_LABEL = "Content-Type";
    private static final String CONTENT_TYPE_JSON = "application/json";
    private static final String STATUS_CODE_LABEL = "status_code";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String MESSAGE = "message";

    private final OkHttpClient client;

    public DBUserDataAccessObject2(OkHttpClient client) {
        this.client = client;
    }

    /**
     * Saves a list of cocktails for a user in the database.
     *
     * @param user The user object containing username and password.
     * @param cocktails The list of cocktails to be saved.
     * @throws DetailPageDataAccessException If there is a problem accessing the database.
     */
    @Override
    public void saveCocktails(User user, List<Cocktail> cocktails) throws DetailPageDataAccessException {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // POST METHOD
        final MediaType mediaType = MediaType.parse(CONTENT_TYPE_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());

        // Serialize the cocktail list
        final JSONArray cocktailsJSON = new JSONArray();
        for (Cocktail cocktail : cocktails) {
            final JSONObject cocktailJson = new JSONObject();
            cocktailJson.put("idDrink", cocktail.getIdDrink());
            cocktailJson.put("strDrink", cocktail.getCocktailName());
            cocktailJson.put("strInstructions", cocktail.getInstructions());
            cocktailJson.put("photoUrl", cocktail.getPhotoLink());
            cocktailJson.put("ingredients", new JSONObject(cocktail.getIngredients()));
            cocktailsJSON.put(cocktailJson);
        }
        requestBody.put("cocktails", cocktailsJSON);

        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) != SUCCESS_CODE) {
                throw new DetailPageDataAccessException("Database error: " + responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DetailPageDataAccessException(ex.getMessage());
        }
    }

    /**
     * Loads a list of cocktails for a user from the database.
     *
     * @param user The user object containing username and password.
     * @return A list of cocktails associated with the user.
     * @throws DetailPageDataAccessException If there is a problem accessing the database.
     * @throws RuntimeException              If there is a problem while running.
     */
    @Override
    public List<Cocktail> loadCocktails(User user) throws DetailPageDataAccessException {
        final String username = user.getName();

        final OkHttpClient client = new OkHttpClient().newBuilder().build();
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();
            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");
                final JSONArray cocktailsArray = userJSONObject.getJSONArray("cocktails");

                final List<Cocktail> cocktails = new ArrayList<>();
                for (int i = 0; i < cocktailsArray.length(); i++) {
                    final JSONObject cocktailJson = cocktailsArray.getJSONObject(i);

                    final Map<String, Object> tempingredients = cocktailJson.getJSONObject("ingredients").toMap();
                    final Map<String, String> ingredients = new HashMap<>();
                    for (Map.Entry<String, ?> entry : tempingredients.entrySet()) {
                        ingredients.put(entry.getKey(), entry.getValue().toString());
                    }

                    final Cocktail cocktail = new CommonCocktail(
                            cocktailJson.getInt("idDrink"),
                            cocktailJson.getString("strDrink"),
                            cocktailJson.getString("strInstructions"),
                            cocktailJson.getString("photoUrl"),
                            ingredients
                    );
                    cocktails.add(cocktail);
                }
                return cocktails;
            }
            else {
                throw new DetailPageDataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }

    /**
     * Fetch User from the name.
     * @param username name of user
     * @return user User.
     * @throws DetailPageDataAccessException the exception.
     */
    public User getUserByUsername(String username) throws DetailPageDataAccessException {
        final Request request = new Request.Builder()
                .url(String.format("http://vm003.teach.cs.toronto.edu:20112/user?username=%s", username))
                .addHeader(CONTENT_TYPE_LABEL, CONTENT_TYPE_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.body() == null) {
                throw new DetailPageDataAccessException("Response body is null.");
            }

            final JSONObject responseBody = new JSONObject(response.body().string());

            if (responseBody.getInt(STATUS_CODE_LABEL) == SUCCESS_CODE) {
                final JSONObject userJSONObject = responseBody.getJSONObject("user");

                // Create a User object using the factory
                final UserFactory userFactory = new CommonUserFactory();
                return userFactory.create(
                        userJSONObject.getString("username"),
                        userJSONObject.getString("password")
                );
            }
            else {
                throw new DetailPageDataAccessException(responseBody.getString(MESSAGE));
            }
        }
        catch (IOException | JSONException ex) {
            throw new DetailPageDataAccessException("Error fetching user: " + ex.getMessage());
        }
    }

}

