package use_case.delete_favorite;

import java.util.List;
import java.util.Map;

import entity.Cocktail;
import entity.User;
import use_case.detailPage.DetailPageDataAccessException;

/**
 * DAO for the Delete Use Case.
 */
public interface DeleteDataAccessInterface {

    /**
     * Load cocktails from user.
     * @param user user
     * @return the map of user and cocktails in DAO.
     * @throws DetailPageDataAccessException throw exceptions.
     */
    List<Cocktail> loadCocktails(User user) throws DetailPageDataAccessException;

    /**
     * Updates the user favorite cocktail list.
     * @param user user
     * @param cocktails the list of cocktails to be saved from the user's favorites.
     * @throws DetailPageDataAccessException throw exceptions.
     */
    void saveCocktails(User user, List<Cocktail> cocktails) throws DetailPageDataAccessException;

    /**
     * Get user by username.
     * @param username username
     * @return user user
     * @throws DetailPageDataAccessException throw exceptions.
     */
    User getUserByUsername(String username) throws DetailPageDataAccessException;
}
