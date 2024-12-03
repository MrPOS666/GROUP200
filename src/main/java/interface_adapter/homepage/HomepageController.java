package interface_adapter.homepage;

import use_case.homepage.HomepageInputBoundary;
import use_case.homepage.HomepageInputData;

/**
 * Controller for the Homepage Use Case.
 */
public class HomepageController {

    private final HomepageInputBoundary homepageInteractor;

    public HomepageController(HomepageInputBoundary homepageInteractor) {
        this.homepageInteractor = homepageInteractor;
    }

    /**
     * Executes the "switch to SearchView" Use Case.
     * @param username username of current user
     */
    public void switchToSearchView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        homepageInteractor.switchToSearchView(homepageInputData);
    }

    /**
     * Executes the "switch to MyFavouritesView" Use Case.
     * @param username username of current use
     */
    public void switchToMyFavouritesView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        homepageInteractor.switchToMyFavouritesView(homepageInputData);
    }

    /**
     * Executes the "switch to RecommendationView" Use Case.
     * @param username username of current use
     */
    public void switchToRecommendationView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        homepageInteractor.switchToRecommendationView(homepageInputData);
    }

    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     * @param username username of current use
     */
    public void switchToChangePasswordView(String username) {
        final HomepageInputData homepageInputData = new HomepageInputData(username);
        homepageInteractor.switchToChangePasswordView(homepageInputData);
    }
}
