package use_case.recommended;

import entity.Cocktail;

import java.util.List;
import java.util.Map;

public class RecommendedOutputData {
    private final boolean useCaseFailed;
    private final List<Integer> idDrink;
    private final List<String> strDrink;
    private final List<String> strInstructions;
    private final List<String> photoUrl;
    private final List<Map<String, String>> ingredients;

    // Constructor
    public RecommendedOutputData(boolean useCaseFailed,
                                 List<Integer> idDrink,
                                 List<String> strDrink,
                                 List<String> strInstructions,
                                 List<String> photoUrl,
                                 List<Map<String, String>> ingredients) {
        this.useCaseFailed = useCaseFailed;
        this.idDrink = idDrink;
        this.strDrink = strDrink;
        this.strInstructions = strInstructions;
        this.photoUrl = photoUrl;
        this.ingredients = ingredients;
    }

    // Getters for accessing variables
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
