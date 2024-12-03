package use_case.homepage;

/**
 * Output Boundary for actions which are related to Homepage use case.
 */
public interface HomepageOutputBoundary {

    /**
     * Switches to the Search View.
     * @param homepageOutputData output data
     */
    void switchToSearchView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the MyFavourites View.
     * @param homepageOutputData output data
     */
    void switchToMyFavouritesView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Recommendation View.
     * @param homepageOutputData output data
     */
    void switchToRecommendationView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the ChangePassword View.
     * @param homepageOutputData output data
     */
    void switchToChangePasswordView(HomepageOutputData homepageOutputData);
}
