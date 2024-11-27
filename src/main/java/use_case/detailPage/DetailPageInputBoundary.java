package use_case.detailPage;

/**
 * Input Boundary for actions which are related to Detail Page.
 */
public interface DetailPageInputBoundary {

    /**
     * Execute to add the current Cocktail into My Favourite List.
     * @param detailPageInputData detailPageInputData
     */
    void execute(DetailPageInputData detailPageInputData);

    /**
     * Return to the original Page.
     */
    void returnOrigin();

    /**
     * Execute the addMyFavourite Use case.
     * @param detailPageInputData detailPageInputData
     */
    void addMyFavourite(DetailPageInputData detailPageInputData);
}
