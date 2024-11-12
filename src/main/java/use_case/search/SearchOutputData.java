package use_case.search;

import java.util.Map;

/**
 * Output Data for the Search Use Case.
 */
public class SearchOutputData {

    private final boolean useCaseFailed;

    private final int idDrink;
    private final String strDrink;
    private final String strInstructions;
    private final String photoUrl;
    // Ingredient name as key, measure as value
    private final Map<String, String> ingredients;

    // Constructor
    public SearchOutputData(boolean useCaseFailed, int idDrink, String strDrink, String strInstructions, String photoUrl, Map<String, String> ingredients) {
        this.useCaseFailed = useCaseFailed;
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

    public String getCocktailName() {
        return strDrink;
    }

    public String getRecipe() {
        return strInstructions;
    }

    public String getPhotoLink() {
        return photoUrl;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
