package use_case.homepage;

/**
 * Output Boundary for actions which are related to Homepage use case.
 */
public interface HomepageOutputBoundary {

    /**
     * Switches to the Search View.
     */
    void switchToSearchView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the MyFavourites View.
     */
    void switchToMyFavouritesView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the Recommendation View.
     */
    void switchToRecommendationView(HomepageOutputData homepageOutputData);

    /**
     * Switches to the ChangePassword View.
     */
    void switchToChangePasswordView(HomepageOutputData homepageOutputData);
}
