package entity;

import java.util.Map;

/**
 * Create Cocktail.
 */
public interface CocktailFactory {
    /**
     * Create Cocktail.
     * @param idDrink id of cocktail
     * @param strDrink name of cocktail
     * @param strInstructions instruction of cocktail
     * @param photoUrl photo URL of cocktail
     * @param ingredients ingredients of cocktail
     * @return Cocktail
     */
    Cocktail create(int idDrink,
                    String strDrink,
                    String strInstructions,
                    String photoUrl,
                    Map<String, String> ingredients,
                    Map<String, String> tags);
}
