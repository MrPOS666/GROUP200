package interface_adapter.search;
import java.util.List;

import java.util.ArrayList;
import java.util.Map;


public class SearchState {
    private String cocktailName = "";
    private Map<String, String> ingredients;
    private String recipe = "";
    private List<String> cocktailNames;
    private String photoLink;
    private String searchError;

    public SearchState(SearchState previousState) {
        this.cocktailName = previousState.cocktailName;
        this.ingredients = previousState.ingredients;
        this.recipe = previousState.recipe;
        this.photoLink = previousState.photoLink;
        this.searchError = previousState.searchError;
    }

    public SearchState() {
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
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
}
