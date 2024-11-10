package interface_adapter.search;

public class SearchState {
    private String cocktailName = "";
    private String searchError;

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

}
