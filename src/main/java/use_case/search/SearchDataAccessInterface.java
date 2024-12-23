package use_case.search;

import entity.Cocktail;

import java.io.IOException;
import java.util.List;

/**
 * DAO for the Search Use Case.
 */
public interface SearchDataAccessInterface {

    /**
     * Checks if a cocktail with the given name exists.
     * @param cocktailName the cocktail name to look for
     * @return true if a cocktail with the given name exists; false otherwise
     */
    boolean existsByName(String cocktailName) throws IOException;

    /**
     * Checks if a cocktail with the given ID exists.
     * @param cocktailId the cocktail ID to look for
     * @return true if a cocktail with the given ID exists; false otherwise
     */
    boolean existsById(int cocktailId) throws IOException;

    /**
     * Returns the cocktail with the given name.
     * @param cocktailName the name of the cocktail to look up
     * @return the cocktail with the given name
     */
    List<Cocktail> getByName(String cocktailName) throws IOException;

    /**
     * Returns the cocktail with the given ID.
     * @param cocktailId the ID of the cocktail to look up
     * @return the cocktail with the given ID
     */
    Cocktail getById(int cocktailId) throws IOException;

    /**
     * Returns the name of the current cocktail in focus within the application.
     * @return the name of the current cocktail; null if no cocktail is currently in focus.
     */
    String getCurrentCocktailName();

    /**
     * Sets the name of the cocktail currently in focus within the application.
     * @param cocktailName the new current cocktail name; null to indicate no cocktail is currently in focus.
     */
    void setCurrentCocktailName(String cocktailName);
}
