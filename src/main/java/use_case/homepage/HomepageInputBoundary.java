package use_case.homepage;

/**
 * Input Boundary for actions which are related to homepage except logout.
 */
public interface HomepageInputBoundary {

    /**
     * Execute the switch to Search view use case.
     * @param homepageInputData Input data of homepage use case
     */
    void switchToSearchView(HomepageInputData homepageInputData);

    /**
     * Execute the switch to MyFavourites view use case.
     * @param homepageInputData Input data of homepage use case
     */
    void switchToMyFavouritesView(HomepageInputData homepageInputData);

    /**
     * Execute the switch to Recommendation view use case.
     * @param homepageInputData Input data of homepage use case
     */
    void switchToRecommendationView(HomepageInputData homepageInputData);

    /**
     * Execute the switch to ChangePassword view use case.
     * @param homepageInputData Input data of homepage use case
     */
    void switchToChangePasswordView(HomepageInputData homepageInputData);
}
