package use_case.search;

import entity.Cocktail;

import java.util.List;

/**
 * The Input Data for the Search Use Case.
 */
public class SearchInputData {

    private final String cocktailName;
    private final boolean isSearchByName;
    private final boolean isSearchByID;
    private final String input;
    public SearchInputData(String cocktailName, boolean isSearchByName, boolean isSearchByID, String input) {
        this.cocktailName = cocktailName;
        this.isSearchByName = isSearchByName;
        this.isSearchByID = isSearchByID;
        this.input = input;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public boolean isSearchByName() { return isSearchByName; }

    public String getInput() { return input; }

    public boolean isSearchById() {
        return isSearchByID;
    }
}
