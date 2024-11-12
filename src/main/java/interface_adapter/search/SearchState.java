package interface_adapter.search;
import java.util.List;

import java.util.ArrayList;


public class SearchState {
    private String cocktailName = "";
    private List<String> ingredients = new ArrayList<>();
    private String recipe = "";
    private List<String> cocktailNames;
    private String searchError;

    public SearchState(SearchState previousState) {
        this.cocktailName = previousState.cocktailName;
        this.ingredients = previousState.ingredients;
        this.recipe = previousState.recipe;
        this.searchError = previousState.searchError;
    }

    public SearchState() {
    }

    public String getCocktailName() {
        return cocktailName;

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

    /**
     * Clears the state to reset for the next search.
     */
    public void clearState() {
        this.cocktailNames = null;
        this.searchError = null;
    }
}
