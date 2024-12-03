package use_case.delete_favorite;

/**
 * The output boundary for the Delete Use Case.
 */
public interface DeleteOutputBoundary {

    /**
     * Prepares the success view for the Delete Use Case.
     * @param deleteOutputData the output data
     */
    void prepareSuccessView(DeleteOutputData deleteOutputData);

    /**
     * Prepares the failure view for the Delete Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);

    /**
     * Switches to the Homepage View.
     */
    void switchToHomepageView();
}
