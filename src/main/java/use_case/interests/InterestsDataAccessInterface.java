package use_case.interests;

import entity.Cocktail;
import entity.User;

import java.util.HashMap;
import java.util.List;

public interface InterestsDataAccessInterface {
    /**
     * Returns the list of the user's favourite cocktails
     * @param username
     * @return list of user's favourites
     */
    User getUser(String username);
}
