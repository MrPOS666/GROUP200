package entity;

import java.util.HashMap;
import java.util.List;

/**
 * The representation of a user in our program.
 */
public interface User {

    /**
     * Returns the username of the user.
     * @return the username of the user.
     */
    String getName();

    /**
     * Returns the password of the user.
     * @return the password of the user.
     */
    String getPassword();

    /**
     * Return the "My favourite" list of Cocktails of the user.
     * @return the "My favourite" list of Cocktails of the user.
     */
    List<entity.Cocktail> getMyFavourite();

    /**
     * Returns the myInterests HashMap of the user.
     * @return the myInterests HashMap of the user.
     */
    HashMap<String, Integer> getMyInterests();
}
