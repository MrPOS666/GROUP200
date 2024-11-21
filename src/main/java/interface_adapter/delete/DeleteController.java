package interface_adapter.delete;

import java.util.List;

import use_case.delete_favorite.DeleteInputBoundary;
import use_case.delete_favorite.DeleteInputData;

/**
 * Controller for the Delete Use Case.
 */
public class DeleteController {
    private final DeleteInputBoundary deleteInteractor;

    public DeleteController(DeleteInputBoundary deleteInteractor) {
        this.deleteInteractor = deleteInteractor;
    }

    /**
     * Executes the Delete Use Case.
     * @param selectCocktail the selected cocktail ID.
     */
    public void execute(List<Integer> selectCocktail) {
        final DeleteInputData deleteInputData = new DeleteInputData(selectCocktail);

        deleteInteractor.execute(deleteInputData);
    }
}
