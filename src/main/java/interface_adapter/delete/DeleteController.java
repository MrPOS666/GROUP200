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
     * @param username the username.
     */
    public void execute(List<Integer> selectCocktail, String username) {
        final DeleteInputData deleteInputData = new DeleteInputData(selectCocktail, username);

        deleteInteractor.execute(deleteInputData);
    }
}
