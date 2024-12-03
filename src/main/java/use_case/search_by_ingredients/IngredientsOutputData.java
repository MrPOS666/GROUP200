package use_case.search_by_ingredients;

import java.util.List;
import java.util.Map;

/**
 * Output Data for the Search Use Case.
 */
public class IngredientsOutputData {

    private final boolean useCaseFailed;

    private final List<Integer> idDrink;
    private final List<String> strDrink;
    private final List<String> strInstructions;
    private final List<String> photoUrl;
    // Ingredient name as key, measure as value
    private final List<Map<String, String>> ingredients;

    // Constructor
    public IngredientsOutputData(boolean useCaseFailed, List<Integer> idDrink, List<String> strDrink, List<String> strInstructions, List<String> photoUrl, List<Map<String, String>> ingredients) {
        this.useCaseFailed = useCaseFailed;
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strInstructions = strInstructions;
        this.photoUrl = photoUrl;
        this.ingredients = ingredients;
    }

    // Getters for accessing fields
    public List<Integer> getIdDrink() {
        return idDrink;
    }

    public List<String> getCocktailName() {
        return strDrink;
    }

    public List<String> getInstructions() {
        return strInstructions;
    }

    public List<String> getPhotoLink() {
        return photoUrl;
    }

    public List<Map<String, String>> getIngredients() {
        return ingredients;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
