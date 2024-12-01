package use_case.delete_favorite;

/**
 * Input Boundary for actions which are related to deleting.
 */
public interface DeleteInputBoundary {

    /**
     * Executes the delete use case.
     * @param deleteInputData the input data
     * @throws MyfavouritePageDataAccessException the exception
     */
    void execute(DeleteInputData deleteInputData) throws MyfavouritePageDataAccessException;

    /**
     * Execute the switch to Homepage View use case.
     */
    void switchToHomepageView();
}
