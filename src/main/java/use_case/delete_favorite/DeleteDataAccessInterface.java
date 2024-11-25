package use_case.delete_favorite;

import java.util.List;

import entity.User;

/**
 * DAO for the Delete Use Case.
 */
public interface DeleteDataAccessInterface {

    /**
     * Returns the user by its name.
     * @param username the username
     * @return user
     */
    User getUserByUsername(String username);

    /**
     * Updates the user favorite cocktail list.
     * @param user user
     * @param deleteIds the list of cocktail IDs to be removed from the user's favorites.
     */
    void updateFavorite(User user, List<Integer> deleteIds);
}
