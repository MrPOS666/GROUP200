package data_access;

import entity.User;
import use_case.delete_favorite.DeleteDataAccessInterface;

public class DeleteAccessObject implements DeleteDataAccessInterface {
    /**
     * Returns the user by its name.
     *
     * @param username the username
     * @return user
     */
    @Override
    public User getUserByUsername(String username) {
        return null;
    }

    /**
     * Updates the user favorite cocktail list.
     *
     * @param user user
     */
    @Override
    public void updateFavorite(User user) {

    }
}
