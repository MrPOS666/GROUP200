package use_case.delete_favorite;

import java.util.List;

import entity.Cocktail;
import entity.User;
import use_case.detailPage.DetailPageDataAccessException;

/**
 * DAO for the Delete Use Case.
 */
public interface DeleteDataAccessInterface {

    /**
     * Update the MyFavourite cocktail list for an existing user.
     *
     * @param user          The user object containing the username and password.
     * @param newFavourites The new list of favorite cocktails.
     * @throws DetailPageDataAccessException If an error occurs during the process.
     */
    void updateMyFavourite(User user, List<Cocktail> newFavourites) throws DetailPageDataAccessException;

    /**
     * Load a user by their username.
     *
     * @param username The username of the user.
     * @return The User object.
     * @throws DetailPageDataAccessException If an error occurs during the process.
     */
    User loadUser(String username) throws DetailPageDataAccessException;

}