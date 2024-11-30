package use_case.search_by_ingredients;

import entity.Cocktail;

import java.util.List;

/**
 * DAO for the Search_By_Ingredients Use Case.
 */
public interface IngredientsDataAccessInterface {

    /**
     * Checks if a cocktail with the given ingredients exists.
     * @param ingredients the cocktail name to look for
     * @return true if a cocktail with the given ingredients exists; false otherwise
     */
    boolean existsByIngredients(List<String> ingredients);


    /**
     * Returns the cocktail with the given ingredients.
     * @param ingredients the name of the cocktail to look up
     * @return the list of cocktail with the given ingredients
     */
    List<Cocktail> getByIngredients(List<String> ingredients);

    /**
     * Returns the cocktail with the given ID.
     * @param cocktailId the ID of the cocktail to look up
     * @return the cocktail with the given ID
     */
    Cocktail getById(int cocktailId);
}
