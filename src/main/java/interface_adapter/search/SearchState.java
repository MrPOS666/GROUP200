package interface_adapter.search;

import java.util.List;

/**
 * The State for the Search Use Case.
 */
public class SearchState {
    private List<String> cocktailNames;
    private String searchError;

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

    /**
     * Clears the state to reset for the next search.
     */
    public void clearState() {
        this.cocktailNames = null;
        this.searchError = null;
    }
}
