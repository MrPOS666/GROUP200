package entity;

import java.util.Map;

/**
 * Cocktail implementation.
 */
public class CommonCocktail implements Cocktail {

    private final int idDrink;
    private final String strDrink;
    private final String strInstructions;
    private final String photoUrl;

    // Ingredient name as key, measure as value
    private final Map<String, String> ingredients;

    // Constructor
    public CommonCocktail(int idDrink,
                          String strDrink,
                          String strInstructions,
                          String photoUrl,
                          Map<String, String> ingredients) {
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strInstructions = strInstructions;
        this.photoUrl = photoUrl;
        this.ingredients = ingredients;
    }

    // Getters for accessing fields
    public int getIdDrink() {
        return idDrink;
    }

    public String getInstructions() {
        return strInstructions;
    }

    public String getCocktailName() {
        return strDrink;
    }

    public String getPhotoLink() {
        return photoUrl;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }
}