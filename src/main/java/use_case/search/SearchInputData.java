package use_case.search;

/**
 * The Input Data for the Search Use Case.
 */
public class SearchInputData {

    private final String cocktailName;

    public SearchInputData(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    String getCocktailName() {
        return cocktailName;
    }

}
