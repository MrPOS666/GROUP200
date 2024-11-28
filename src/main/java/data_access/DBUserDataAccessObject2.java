package data_access;

import java.io.IOException;
import java.util.*;

import entity.*;
import okhttp3.*;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import use_case.detailPage.DetailPageDataAccessException;
import use_case.detailPage.DetailPageDataAccessInterface;

/**
 * DAO class for managing user data and their associated favorite cocktails.
 */
public class DBUserDataAccessObject2 implements DetailPageDataAccessInterface {

    private static final String BASE_URL = "http://vm003.teach.cs.toronto.edu:20112";
    private static final String MODIFY_USER_INFO_ENDPOINT = "/modifyUserInfo";
    private static final String CREATE_USER_ENDPOINT = "/user";
    private static final String GET_USER_ENDPOINT = "/user";
    private static final MediaType JSON = MediaType.parse("application/json");

    @Override
    public User getUser(String username) {
        try {
            return this.loadUser(username);
        }
        catch (DetailPageDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void addMyFavourite(User user, List<Cocktail> newFavourites) {
        try {
            this.updateMyFavourite(user, newFavourites);
        }
        catch (DetailPageDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Save a new user with an empty MyFavourite cocktail list.
     *
     * @param user The user object containing the username and password.
     * @throws DetailPageDataAccessException If an error occurs during the process.
     */
    public void saveUser(User user) throws DetailPageDataAccessException {
        final OkHttpClient client = new OkHttpClient();
        final JSONObject requestBody = new JSONObject();
        requestBody.put("username", user.getName());
        requestBody.put("password", user.getPassword());
        requestBody.put("info", new JSONObject().put("myFavourite", new JSONArray()));

        final RequestBody body = RequestBody.create(requestBody.toString(), JSON);
        final Request request = new Request.Builder()
                .url(BASE_URL + CREATE_USER_ENDPOINT)
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new DetailPageDataAccessException("Error saving user: " + response.message());
            }
        }
        catch (IOException e) {
            throw new DetailPageDataAccessException("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Update the MyFavourite cocktail list for an existing user.
     *
     * @param user          The user object containing the username and password.
     * @param newFavourites The new list of favorite cocktails.
     * @throws DetailPageDataAccessException If an error occurs during the process.
     */
    public void updateMyFavourite(User user, List<Cocktail> newFavourites) throws DetailPageDataAccessException {
        final OkHttpClient client = new OkHttpClient();
        final JSONObject requestBody = new JSONObject();
        requestBody.put("username", user.getName());
        requestBody.put("password", user.getPassword());

        final JSONArray favouritesArray = new JSONArray();
        for (Cocktail cocktail : newFavourites) {
            final JSONObject cocktailJson = new JSONObject();
            cocktailJson.put("idDrink", cocktail.getIdDrink());
            cocktailJson.put("strDrink", cocktail.getCocktailName());
            cocktailJson.put("strInstructions", cocktail.getInstructions());
            cocktailJson.put("photoUrl", cocktail.getPhotoLink());
            cocktailJson.put("ingredients", new JSONObject(cocktail.getIngredients()));
            favouritesArray.put(cocktailJson);
        }
        requestBody.put("info", new JSONObject().put("myFavourite", favouritesArray));

        final RequestBody body = RequestBody.create(requestBody.toString(), JSON);
        final Request request = new Request.Builder()
                .url(BASE_URL + MODIFY_USER_INFO_ENDPOINT)
                .put(body)
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new DetailPageDataAccessException("Error updating MyFavourite: " + response.message());
            }
        }
        catch (IOException e) {
            throw new DetailPageDataAccessException("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Load a user by their username.
     *
     * @param username The username of the user.
     * @return The User object.
     * @throws DetailPageDataAccessException If an error occurs during the process.
     */
    public User loadUser(String username) throws DetailPageDataAccessException {
        final OkHttpClient client = new OkHttpClient();
        final HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("vm003.teach.cs.toronto.edu")
                .port(20112)
                .addPathSegment("user")
                .addQueryParameter("username", username)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Content-Type", "application/json")
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                final JSONObject responseBody = new JSONObject(response.body().string());
                final JSONObject userObject = responseBody.getJSONObject("user");

                final String name = userObject.getString("username");
                final String password = userObject.getString("password");
                final JSONObject info = userObject.optJSONObject("info");

                final List<Cocktail> favourites = new ArrayList<>();
                if (info != null && info.has("myFavourite")) {
                    final JSONArray favouritesArray = info.getJSONArray("myFavourite");
                    for (int i = 0; i < favouritesArray.length(); i++) {
                        final JSONObject cocktailJson = favouritesArray.getJSONObject(i);
                        final Map<String, String> ingredients = new HashMap<>();
                        final JSONObject ingredientsJson = cocktailJson.getJSONObject("ingredients");
                        for (String key : ingredientsJson.keySet()) {
                            ingredients.put(key, ingredientsJson.getString(key));
                        }
                        final String photoUrl = cocktailJson.optString("photoUrl");
                        final CommonCocktail cocktail = new CommonCocktail(
                                cocktailJson.optInt("idDrink"),
                                cocktailJson.optString("strDrink"),
                                cocktailJson.optString("strInstructions"),
                                photoUrl,
                                ingredients,
                                ImageDataAccessObject.fetchImage(photoUrl)
                        );
                        favourites.add(cocktail);
                    }
                }

                return new CommonUser(name, password, favourites);
            }
            else {
                throw new DetailPageDataAccessException("Error loading user: " + response.message());
            }
        }
        catch (IOException | JSONException e) {
            throw new DetailPageDataAccessException("Exception occurred: " + e.getMessage());
        }
    }

    /**
     * Change the password for an existing user.
     *
     * @param user        The user object containing the username and current password.
     * @param newPassword The new password for the user.
     * @throws DetailPageDataAccessException If an error occurs during the process.
     */
    public void changePassword(User user, String newPassword) {
        final OkHttpClient client = new OkHttpClient().newBuilder().build();

        // Prepare request body
        final MediaType mediaType = MediaType.parse("application/json");
        final JSONObject requestBody = new JSONObject();
        requestBody.put("username", user.getName());
        // Update to the new password
        requestBody.put("password", newPassword);

        // Transport the current MyFavourite list
        final JSONArray favouritesArray = new JSONArray();
        for (Cocktail cocktail : user.getMyFavourite()) {
            final JSONObject cocktailJson = new JSONObject();
            cocktailJson.put("idDrink", cocktail.getIdDrink());
            cocktailJson.put("strDrink", cocktail.getCocktailName());
            cocktailJson.put("strInstructions", cocktail.getInstructions());
            cocktailJson.put("photoUrl", cocktail.getPhotoLink());
            cocktailJson.put("ingredients", new JSONObject(cocktail.getIngredients()));
            favouritesArray.put(cocktailJson);
        }
        requestBody.put("info", new JSONObject().put("myFavourite", favouritesArray));

        // Build HTTP request
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader("Content-Type", "application/json")
                .build();

        try {
            final Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                final JSONObject responseBody = new JSONObject(response.body().string());

                if (responseBody.getInt("status_code") == 200) {
                    // Password updated successfully
                }
                else {
                    throw new RuntimeException(responseBody.getString("message"));
                }
            }
            else {
                throw new RuntimeException("HTTP error: " + response.code());
            }
        }
        catch (IOException | JSONException ex) {
            throw new RuntimeException(ex);
        }
    }
}


