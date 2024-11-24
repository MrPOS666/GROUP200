package use_case.delete_favorite;

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
     */
    void updateFavorite(User user);
}
