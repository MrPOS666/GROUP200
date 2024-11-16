package entity;

import java.util.Map;

/**
 * The representation of a cocktail in our program.
 */
public interface Cocktail {
    /**
     * Returns the information of the cocktail.
     * @return the name of the user.
     */
    String getCocktailName();

    /**
     * Returns the ingredient of the cocktail.
     * @return the ingredient of the cocktail.
     */
    Map<String, String> getIngredients();

    /**
     * Returns the photo link of the cocktail.
     * @return the photo link
     */
    String getPhotoLink();

    /**
     * Returns the id of the cocktail.
     * @return the id.
     */
    int getIdDrink();

    /**
     * Returns the instructions of the cocktail.
     * @return the instructions.
     */
    String getInstructions();
}