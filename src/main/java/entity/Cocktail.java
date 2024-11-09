package entity;

import java.util.List;

/**
 * The representation of a cocktail in our program.
 */
public interface Cocktail {
    /**
     * Returns the information of the cocktail.
     * @return the name of the user.
     */
    String getName();

    /**
     * Returns the recipe of the cocktail.
     * @return the name of the recipe.
     */
    String getRecipe();

    /**
     * Returns the ingredient of the cocktail.
     * @return the ingredient of the cocktail.
     */
    List<String> getIngredients();

    /**
     * Returns the photo link of the cocktail.
     * @return the photo link
     */
    String getPhotoLink();
}
