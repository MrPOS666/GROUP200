package use_case.search;

/**
 * Output Data for the Search Use Case.
 */
public class SearchOutputData {

    private final String cocktailName;
    private final boolean useCaseFailed;

    public SearchOutputData(String cocktailName, boolean useCaseFailed) {
        this.cocktailName = cocktailName;
        this.useCaseFailed = useCaseFailed;
    }

    public String getCocktailName() {
        return cocktailName;
    }
}
