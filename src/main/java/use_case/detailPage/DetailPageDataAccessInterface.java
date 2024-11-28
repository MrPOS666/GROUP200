package use_case.detailPage;

import java.util.List;

import entity.Cocktail;
import entity.User;

/**
 * Interface of Detail Page Use Case.
 */
public interface DetailPageDataAccessInterface {

    /**
     * Get User from DAO.
     * @param username name of the user.
     * @return current user with name, password, and MyFavourite list.
     */
    User getUser(String username);

    /**
     * Update the MyFavourite cocktail list for an existing user.
     * @param user The user object containing the username and password.
     * @param newFavourites The new list of favorite cocktails.
     */
    void addMyFavourite(User user, List<Cocktail> newFavourites);
}
