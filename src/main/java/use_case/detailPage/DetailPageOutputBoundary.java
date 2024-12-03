package use_case.detailPage;

/**
 * The output boundary for the Detail Page Use Case.
 */
public interface DetailPageOutputBoundary {
    /**
     * Prepare success view for Detail Page.
     * @param detailPageOutputData detailPageOutputData.
     */
    void prepareSuccessView(DetailPageOutputData detailPageOutputData);

    /**
     * Prepare failure view for Detail Page.
     * @param detailPageOutputData detailPageOutputData
     */
    void prepareFailView(DetailPageOutputData detailPageOutputData);

    /**
     * Return to the original View.
     * @param viewModelName name of the original view model
     */
    void returnOrigin(String viewModelName);
}
