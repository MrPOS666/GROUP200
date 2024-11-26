package use_case.homepage;

/**
 * Output Boundary for actions which are related to Homepage use case.
 */
public interface HomepageOutputBoundary {

    /**
     * Switches to the Search View.
     */
    void switchToSearchView();

    /**
     * Switches to the MyFavourites View.
     */
    void switchToMyFavouritesView();

    /**
     * Switches to the Recommendation View.
     */
    void switchToRecommendationView();

    /**
     * Switches to the ChangePassword View.
     */
    void switchToChangePasswordView();
}