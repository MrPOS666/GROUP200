package use_case.search;

import java.util.List;
import java.util.Map;

/**
 * Output Data for the Search Use Case.
 */
public class SearchOutputData {

    private final List<String> cocktailNames;

    private final boolean useCaseFailed;

    public SearchOutputData(List<String> cocktailNames, boolean useCaseFailed) {
        this.cocktailNames = cocktailNames;
        this.useCaseFailed = useCaseFailed;
    }

    public List<String> getCocktailNames() {
        return cocktailNames;
    }

    public boolean isUseCaseFailed() {
        return useCaseFailed;
    }
}
