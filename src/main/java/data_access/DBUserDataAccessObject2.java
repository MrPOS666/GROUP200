package data_access;

import java.io.IOException;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import entity.*;
import okhttp3.*;
import use_case.change_password.ChangePasswordUserDataAccessInterface;
import use_case.delete_favorite.DeleteDataAccessInterface;
import use_case.delete_favorite.MyfavouritePageDataAccessException;
import use_case.detailPage.DetailPageDataAccessException;
import use_case.detailPage.DetailPageDataAccessInterface;
import use_case.login.LoginUserDataAccessInterface;
import use_case.logout.LogoutUserDataAccessInterface;
import use_case.signup.SignupUserDataAccessInterface;

/**
 * DAO class for managing user data and their associated favorite cocktails.
 */
public class DBUserDataAccessObject2 implements DetailPageDataAccessInterface,
                                                SignupUserDataAccessInterface,
                                                LoginUserDataAccessInterface,
                                                ChangePasswordUserDataAccessInterface,
                                                LogoutUserDataAccessInterface,
                                                DeleteDataAccessInterface {

    public static final String ID_DRINK = "idDrink";
    private static final String BASE_URL = "http://vm003.teach.cs.toronto.edu:20112";
    private static final String MODIFY_USER_INFO_ENDPOINT = "/modifyUserInfo";
    private static final String CREATE_USER_ENDPOINT = "/user";
    private static final String GET_USER_ENDPOINT = "/user";
    private static final String APPLICATION_JSON = "application/json";
    private static final MediaType JSON = MediaType.parse(APPLICATION_JSON);

    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String INFO = "info";
    private static final String MY_FAVOURITE = "myFavourite";
    private static final String CONTENT_TYPE = "Content-Type";
    private static final String EXCEPTION_OCCURRED = "Exception occurred: ";
    private static final String STR_DRINK = "strDrink";
    private static final String STR_INSTRUCTIONS = "strInstructions";
    private static final String PHOTO_URL = "photoUrl";
    private static final String INGREDIENTS = "ingredients";

    @Override
    public User getUser(String username) {
        try {
            return this.loadUser(username);
        }
        catch (DetailPageDataAccessException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void addMyFavourite(User user, List<Cocktail> newFavourites) {
        try {
            this.updateMyFavourite(user, newFavourites);
        }
        catch (DetailPageDataAccessException exception) {
            throw new RuntimeException(exception);
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
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());
        requestBody.put(INFO, new JSONObject().put(MY_FAVOURITE, new JSONArray()));

        final RequestBody body = RequestBody.create(requestBody.toString(), JSON);
        final Request request = new Request.Builder()
                .url(BASE_URL + CREATE_USER_ENDPOINT)
                .post(body)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new DetailPageDataAccessException("Error saving user: " + response.message());
            }
        }
        catch (IOException evt) {
            throw new DetailPageDataAccessException(EXCEPTION_OCCURRED + evt.getMessage());
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
        requestBody.put(USERNAME, user.getName());
        requestBody.put(PASSWORD, user.getPassword());

        final JSONArray favouritesArray = new JSONArray();
        for (Cocktail cocktail : newFavourites) {
            final JSONObject cocktailJson = new JSONObject();
            cocktailJson.put(ID_DRINK, cocktail.getIdDrink());
            cocktailJson.put(STR_DRINK, cocktail.getCocktailName());
            cocktailJson.put(STR_INSTRUCTIONS, cocktail.getInstructions());
            cocktailJson.put(PHOTO_URL, cocktail.getPhotoLink());
            cocktailJson.put(INGREDIENTS, new JSONObject(cocktail.getIngredients()));
            favouritesArray.put(cocktailJson);
        }
        requestBody.put(INFO, new JSONObject().put(MY_FAVOURITE, favouritesArray));

        final RequestBody body = RequestBody.create(requestBody.toString(), JSON);
        final Request request = new Request.Builder()
                .url(BASE_URL + MODIFY_USER_INFO_ENDPOINT)
                .put(body)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new DetailPageDataAccessException("Error updating MyFavourite: " + response.message());
            }
        }
        catch (IOException evt) {
            throw new DetailPageDataAccessException(EXCEPTION_OCCURRED + evt.getMessage());
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
                .addQueryParameter(USERNAME, username)
                .build();

        final Request request = new Request.Builder()
                .url(url)
                .get()
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                final JSONObject responseBody = new JSONObject(response.body().string());
                final JSONObject userObject = responseBody.getJSONObject("user");

                final String name = userObject.getString(USERNAME);
                final String password = userObject.getString(PASSWORD);
                final JSONObject info = userObject.optJSONObject(INFO);

                final List<Cocktail> favourites = new ArrayList<>();
                if (info != null && info.has(MY_FAVOURITE)) {
                    final JSONArray favouritesArray = info.getJSONArray(MY_FAVOURITE);
                    for (int i = 0; i < favouritesArray.length(); i++) {
                        final JSONObject cocktailJson = favouritesArray.getJSONObject(i);
                        final Map<String, String> ingredients = new HashMap<>();
                        final JSONObject ingredientsJson = cocktailJson.getJSONObject(INGREDIENTS);
                        for (String key : ingredientsJson.keySet()) {
                            ingredients.put(key, ingredientsJson.getString(key));
                        }
                        final String photoUrl = cocktailJson.optString(PHOTO_URL);
                        final CommonCocktail cocktail = new CommonCocktail(
                                cocktailJson.optInt("idDrink"),
                                cocktailJson.optString(STR_DRINK),
                                cocktailJson.optString(STR_INSTRUCTIONS),
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
            throw new DetailPageDataAccessException(EXCEPTION_OCCURRED + e.getMessage());
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
        final MediaType mediaType = MediaType.parse(APPLICATION_JSON);
        final JSONObject requestBody = new JSONObject();
        requestBody.put(USERNAME, user.getName());
        // Update to the new password
        requestBody.put(PASSWORD, newPassword);

        // Transport the current MyFavourite list
        final JSONArray favouritesArray = new JSONArray();
        for (Cocktail cocktail : user.getMyFavourite()) {
            final JSONObject cocktailJson = new JSONObject();
            cocktailJson.put(ID_DRINK, cocktail.getIdDrink());
            cocktailJson.put(STR_DRINK, cocktail.getCocktailName());
            cocktailJson.put(STR_INSTRUCTIONS, cocktail.getInstructions());
            cocktailJson.put(PHOTO_URL, cocktail.getPhotoLink());
            cocktailJson.put(INGREDIENTS, new JSONObject(cocktail.getIngredients()));
            favouritesArray.put(cocktailJson);
        }
        requestBody.put(INFO, new JSONObject().put(MY_FAVOURITE, favouritesArray));

        // Build HTTP request
        final RequestBody body = RequestBody.create(requestBody.toString(), mediaType);
        final Request request = new Request.Builder()
                .url("http://vm003.teach.cs.toronto.edu:20112/modifyUserInfo")
                .method("PUT", body)
                .addHeader(CONTENT_TYPE, APPLICATION_JSON)
                .build();

        try {
            final Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                final JSONObject responseBody = new JSONObject(response.body().string());

                if (responseBody.getInt("status_code") == 200) {
                    return;
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

    @Override
    public void changePassword(User user) {
        changePassword(user, user.getPassword());
    }

    @Override
    public User get(String username) {
        try {
            return this.loadUser(username);
        }
        catch (DetailPageDataAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCurrentUsername() {
        return null;
    }

    @Override
    public void setCurrentUsername(String username) {
    }

    /**
     * Update the MyFavourite cocktail list for an existing user.
     *
     * @param user          The user object containing the username and password.
     * @param newFavourites The new list of favorite cocktails.
     * @throws MyfavouritePageDataAccessException If an error occurs during the process.
     */
    @Override
    public void updateMyFavouriteCocktail(User user, List<Cocktail> newFavourites) throws MyfavouritePageDataAccessException, DetailPageDataAccessException {
        updateMyFavourite(user, newFavourites);
    }

    /**
     * Load a user by their username.
     *
     * @param username The username of the user.
     * @return The User object.
     * @throws DetailPageDataAccessException If an error occurs during the process.
     */
    @Override
    public User loadUserByName(String username) throws MyfavouritePageDataAccessException, DetailPageDataAccessException {
        return loadUser(username);
    }

    @Override
    public void saveUserToApi(User testUser) throws MyfavouritePageDataAccessException, DetailPageDataAccessException {
        saveUser(testUser);
    }

    @Override
    public boolean existsByName(String username) {
        try {
            final User user = loadUser(username);
            return user != null;
        }
        catch (DetailPageDataAccessException exception) {
            return false;
        }
    }

    @Override
    public void save(User user) {
        try {
            saveUser(user);
        }
        catch (DetailPageDataAccessException exception) {
            throw new RuntimeException("Error saving user: " + exception.getMessage());
        }
    }
}
