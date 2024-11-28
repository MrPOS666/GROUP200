package entity;

import java.util.Map;

/**
 * Factory for creating CommonCocktail objects.
 */
public class CommonCocktailFactory implements CocktailFactory {

    /**
     * Create Cocktail.
     *
     * @param idDrink         id of cocktail
     * @param strDrink        name of cocktail
     * @param strInstructions instruction of cocktail
     * @param photoUrl        photo URL of cocktail
     * @param ingredients     ingredients of cocktail
     * @return Cocktail
     */
    @Override
    public Cocktail create(int idDrink,
                           String strDrink,
                           String strInstructions,
                           String photoUrl,
                           Map<String, String> ingredients,
                           Map<String, String> tags) {
        return new CommonCocktail(idDrink, strDrink, strInstructions, photoUrl, ingredients, tags);
    }
}
