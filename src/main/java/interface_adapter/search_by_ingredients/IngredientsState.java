package interface_adapter.search_by_ingredients;

import java.util.List;
import java.util.Map;

public class IngredientsState {
    private int id = 0;
    private List<Integer> idList;
    private List<Map<String, String>> ingredientsList;
    private List<String> recipeList;
    private List<String> cocktailNamesList;
    private List<String> photoLinkList;

    private String searchError;
    private String input;

    public IngredientsState() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getCocktailNamesList() {
        return cocktailNamesList;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setCocktailNamesList(List<String> cocktailNames) {
        this.cocktailNamesList = cocktailNames;
    }

    public void setSearchError(String searchError) {
        this.searchError = searchError;
    }

    public String getIngredientsToString(Map<String, String> ingredientsMap) {
        String ingredients = "";
        for (Map.Entry<String, String> entry: ingredientsMap.entrySet()) {
            ingredients += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return ingredients;
    }

    /**
     * Clears the state to reset for the next search.
     */
    public void clearState() {
        this.cocktailNamesList = null;
        this.searchError = null;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public void setIdList(List<Integer> idList) {
        this.idList = idList;
    }

    public List<Map<String, String>> getIngredientsList() {
        return ingredientsList;
    }

    public void setIngredientsList(List<Map<String, String>> ingredientsList) {
        this.ingredientsList = ingredientsList;
    }

    public List<String> getPhotoLinkList() {
        return photoLinkList;
    }

    public void setPhotoLinkList(List<String> photoLinkList) {
        this.photoLinkList = photoLinkList;
    }

    public List<String> getRecipeList() {
        return recipeList;
    }

    public void setRecipeList(List<String> recipeList) {
        this.recipeList = recipeList;
    }
}
