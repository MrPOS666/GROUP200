package interface_adapter.search;
import java.util.List;

import java.util.Map;

public class SearchState {
    private String cocktailName = "";
    private int id = 0;
    private List<int> idList;
    private Map<String, String> ingredients;
    private List<Map<String, String>> ingredientsList;
    private List<String> recipeList;
    private String recipe = "";
    private List<String> cocktailNamesList;
    private String photoLink;
    private List<String> photoLinkList;

    private String searchError;
    private boolean isSearchByName;
    private boolean isSearchByID;
    private String input;

    //TODO: make the instance variables be lists
    public SearchState() {
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
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

    public String getRecipe() {
        return recipe;
    }

    public void setRecipe(String recipe) {
        this.recipe = recipe;
    }

    //TODO: Fix once the instance variables become lists
    public String getIngredientsToString() {
        String ingredients = "";
        for (Map.Entry<String, String> entry: this.ingredients.entrySet()) {
            ingredients += entry.getKey() + ": " + entry.getValue() + "\n";
        }
        return ingredients;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getPhotoLink() {
        return photoLink;
    }

    public void setPhotoLink(String photoLink) {
        this.photoLink = photoLink;
    }
    /**
     * Clears the state to reset for the next search.
     */
    public void clearState() {
        this.cocktailNamesList = null;
        this.searchError = null;
    }
    public boolean isSearchByID() {
        return isSearchByID;
    }

    public void setSearchByID(boolean searchByID) {
        isSearchByID = searchByID;
    }

    public boolean isSearchByName() {
        return isSearchByName;
    }

    public void setSearchByName(boolean searchByName) {
        isSearchByName = searchByName;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public List<int> getIdList() {
        return idList;
    }

    public void setIdList(List<int> idList) {
        this.idList = idList;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
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
