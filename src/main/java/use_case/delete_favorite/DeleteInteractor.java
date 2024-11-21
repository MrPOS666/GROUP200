package use_case.delete_favorite;

/**
 * The Delete Interactor.
 */
public class DeleteInteractor implements DeleteInputBoundary {
    private final DeleteDataAccessInterface deleteDataAccessObject;
    private final DeleteOutputBoundary deletePresenter;

    public DeleteInteractor(DeleteDataAccessInterface deleteDataAccessObject, DeleteOutputBoundary deletePresenter) {
        this.deleteDataAccessObject = deleteDataAccessObject;
        this.deletePresenter = deletePresenter;
    }

    /**
     * Executes the delete use case.
     *
     * @param deleteInputData the input data
     */
    @Override
    public void execute(DeleteInputData deleteInputData) {

        if (deleteInputData)
    }
}
