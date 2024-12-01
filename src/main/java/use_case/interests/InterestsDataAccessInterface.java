package use_case.interests;

import entity.Cocktail;
import entity.User;

import java.util.HashMap;
import java.util.List;

public interface InterestsDataAccessInterface {
    /**
     * Returns the list of the user's favourite cocktails
     * @param user
     * @return list of user's favourites
     */
    List<Cocktail> getUserFavourites(User user);


    List<String> getCocktailTags(Cocktail cocktail);
}
