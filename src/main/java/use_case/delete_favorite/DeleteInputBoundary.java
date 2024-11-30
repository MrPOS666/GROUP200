package use_case.delete_favorite;

import use_case.detailPage.DetailPageDataAccessException;

/**
 * Input Boundary for actions which are related to deleting.
 */
public interface DeleteInputBoundary {

    /**
     * Executes the delete use case.
     * @param deleteInputData the input data
     * @throws DetailPageDataAccessException the exception
     */
    void execute(DeleteInputData deleteInputData) throws DetailPageDataAccessException;

    /**
     * Execute the switch to Homepage View use case.
     */
    void switchToHomepageView();
}
