package use_case.search;

import entity.Cocktail;
import entity.User;

/**
 * DAO for the Search Use Case.
 */
//TODO: modify the description.
public interface SearchDataAccessInterface {

    /**
     * Checks if the given username exists.
     * @param cocktailName the cocktail name to look for
     * @return true if a user with the given cocktail exists; false otherwise
     */
    boolean existsByName(String cocktailName);

    /**
     * Saves the user.
     * @param cocktail the user to save
     */
    void save(Cocktail cocktail);

    /**
     * Returns the user with the given username.
     * @param cocktailName the username to look up
     * @return the user with the given username
     */
    User get(String cocktailName);

    /**
     * Returns the username of the curren user of the application.
     * @return the username of the current user; null indicates that no one is logged into the application.
     */
    String getCurrentCocktail();

    /**
     * Sets the username indicating who is the current user of the application.
     * @param cocktailName the new current username; null to indicate that no one is currently logged into the application.
     */
    void setCurrentCocktail(String cocktailName);
}
