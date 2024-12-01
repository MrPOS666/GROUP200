package interface_adapter.recommended;

import java.awt.image.BufferedImage;

import java.util.List;
import java.util.Map;

public class RecommendedState {
    private List<Integer> idList;
    private List<Map<String, String>> ingredientsList;
    private List<String> recipeList;
    private List<String> cocktailNamesList;
    private List<String> photoLinkList;
    private List<BufferedImage> images;

    private String username;

    public RecommendedState() {}

    public List<String> getCocktailNamesList() {
        return cocktailNamesList;
    }

    public List<BufferedImage> getImages() {
        return images;
    }

    public void setImages(List<BufferedImage> images) {
        this.images = images;
    }

    public void setCocktailNamesList(List<String> cocktailNames) {
        this.cocktailNamesList = cocktailNames;
    }

    public String getIngredientsToString(Map<String, String> ingredientsMap) {
        String ingredients = "";
        for (Map.Entry<String, String> entry: ingredientsMap.entrySet()) {
            ingredients += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return ingredients;
    }



}
