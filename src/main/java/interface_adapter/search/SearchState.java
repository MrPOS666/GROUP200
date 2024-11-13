package interface_adapter.search;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;


public class SearchState {
    private String cocktailName = "";
    private String id = "";
    private Map<String, String> ingredients;
    private String recipe = "";
    private List<String> cocktailNames;
    private String photoLink;
    private String searchError;

    private boolean isSearchByName;
    private boolean isSearchByID;
    private String input;

    public SearchState(SearchState previousState) {
        this.cocktailName = previousState.cocktailName;
        this.id = previousState.id;
        this.ingredients = previousState.ingredients;
        this.recipe = previousState.recipe;
        this.photoLink = previousState.photoLink;
        this.searchError = previousState.searchError;
        this.isSearchByName = previousState.isSearchByName;
        this.isSearchByID = previousState.isSearchByID;
        this.input = previousState.input;
    }

    public SearchState() {
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getCocktailNames() {
        return cocktailNames;
    }

    public String getSearchError() {
        return searchError;
    }

    public void setCocktailNames(List<String> cocktailNames) {
        this.cocktailNames = cocktailNames;
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

    public Map<String, String> getIngredients() {
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
        this.cocktailNames = null;
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
}
