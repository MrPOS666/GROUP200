package use_case.delete_favorite;

import java.util.List;

/**
 * The Input Data for the Delete Use Case.
 */
public class DeleteInputData {

    private final List<Integer> deleteCocktailNames;

    public DeleteInputData(List<Integer> deleteCocktailNames) {
        this.deleteCocktailNames = deleteCocktailNames;
    }

    public List<Integer> getDeleteCocktailNames() {
        return deleteCocktailNames;
    }
}
