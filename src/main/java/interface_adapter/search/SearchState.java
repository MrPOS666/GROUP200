package interface_adapter.search;

import java.util.ArrayList;
import java.util.List;

public class SearchState {
    private String cocktailName = "";
    private List<String> ingredients = new ArrayList<>();
    private String recipe = "";
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
    }

    public String getSearchError() {
        return searchError;
    }

    public void setCocktailName(String cocktailName) {
        this.cocktailName = cocktailName;
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
}
