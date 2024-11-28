package use_case.interests_build;

import entity.Cocktail;
import entity.User;

import java.util.HashMap;
import java.util.List;

public interface interests_buildUserDataAccessInterface {
    /**
     * Returns the list of the user's favourite cocktails
     * @param user
     * @return list of user's favourites
     */
    List<Cocktail> getUserFavourites(User user);

    /**
     * Setter method to update user's interests HashMap
     * @param interestsHashMap
     * @param user
     */
    void setUserInterests(User user, HashMap<String, Integer> interestsHashMap);
}
