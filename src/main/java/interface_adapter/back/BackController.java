package interface_adapter.back;

import use_case.back.BackInputBoundary;
import use_case.back.BackInputData;

/**
 * The controller for the Back Use Case.
 */
public class BackController {

    private BackInputBoundary backUseCaseInteractor;

    public BackController(BackInputBoundary backUseCaseInteractor) {
        this.backUseCaseInteractor = backUseCaseInteractor;
    }

    /**
     * Executes the Back Use Case.
     */

    public void back(String cocktailName) {
        // TODO: run the use case interactor for the back use case.
        // 1. instantiate
        // 2. tell the interactor to execute.
        BackInputData backInputData = new BackInputData(cocktailName);
        backUseCaseInteractor.execute(inputData);
    }
}
