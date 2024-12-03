package use_case.delete_favorite;

import java.util.List;

/**
 * The Input Data for the Delete Use Case.
 */
public class DeleteInputData {

    private final List<Integer> deleteCocktailIds;
    private final String userName;

    public DeleteInputData(List<Integer> deleteCocktailNames, String userName) {
        this.deleteCocktailIds = deleteCocktailNames;
        this.userName = userName;
    }

    public List<Integer> getDeleteCocktailId() {
        return deleteCocktailIds;
    }

    public String getUserName() {
        return userName;
    }
}
