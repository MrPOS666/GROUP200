package use_case.delete_favorite;

import java.util.List;

import entity.Cocktail;
import entity.User;
import use_case.delete_favorite.MyfavouritePageDataAccessException;

/**
 * DAO for the Delete Use Case.
 */
public interface DeleteDataAccessInterface {

    /**
     * Update the MyFavourite cocktail list for an existing user.
     *
     * @param user          The user object containing the username and password.
     * @param newFavourites The new list of favorite cocktails.
     * @throws MyfavouritePageDataAccessException If an error occurs during the process.
     */
    void updateMyFavouriteCocktail(User user, List<Cocktail> newFavourites) throws MyfavouritePageDataAccessException;

    /**
     * Load a user by their username.
     *
     * @param username The username of the user.
     * @return The User object.
     * @throws MyfavouritePageDataAccessException If an error occurs during the process.
     */
    User loadUserByName(String username) throws MyfavouritePageDataAccessException;

    void saveUserToApi(User testUser) throws MyfavouritePageDataAccessException;
}