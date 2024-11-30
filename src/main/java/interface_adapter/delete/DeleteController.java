package interface_adapter.delete;

import java.util.List;

import use_case.delete_favorite.DeleteInputBoundary;
import use_case.delete_favorite.DeleteInputData;
import use_case.detailPage.DetailPageDataAccessException;

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
     * @throws RuntimeException runtime exception
     */
    public void execute(List<Integer> selectCocktail, String username) {
        final DeleteInputData deleteInputData = new DeleteInputData(selectCocktail, username);

        try {
            deleteInteractor.execute(deleteInputData);
        }
        catch (DetailPageDataAccessException evt) {
            throw new RuntimeException(evt);
        }
    }

    public void switchToHomepageView() {
        deleteInteractor.switchToHomepageView();
    }
}
