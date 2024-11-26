package use_case.homepage;

/**
 * Input Boundary for actions which are related to homepage except logout.
 */
public interface HomepageInputBoundary {

    /**
     * Execute the switch to Search view use case.
     */
    void switchToSearchView();

    /**
     * Execute the switch to MyFavourites view use case.
     */
    void switchToMyFavouritesView();

    /**
     * Execute the switch to Recommendation view use case.
     */
    void switchToRecommendationView();

    /**
     * Execute the switch to ChangePassword view use case.
     */
    void switchToChangePasswordView();
}