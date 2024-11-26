package interface_adapter.homepage;

import use_case.homepage.HomepageInputBoundary;

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
     */
    public void switchToSearchView() {
        homepageInteractor.switchToSearchView();
    }

    /**
     * Executes the "switch to MyFavouritesView" Use Case.
     */
    public void switchToMyFavouritesView() {
        homepageInteractor.switchToMyFavouritesView();
    }

    /**
     * Executes the "switch to RecommendationView" Use Case.
     */
    public void switchToRecommendationView() {
        homepageInteractor.switchToRecommendationView();
    }

    /**
     * Executes the "switch to ChangePasswordView" Use Case.
     */
    public void switchToChangePasswordView() {
        homepageInteractor.switchToChangePasswordView();
    }
}