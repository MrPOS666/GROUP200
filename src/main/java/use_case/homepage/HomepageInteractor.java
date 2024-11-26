package use_case.homepage;

/**
 * The Homepage Use case.
 */
public class HomepageInteractor implements HomepageInputBoundary {

    private final HomepageOutputBoundary homepagePresenter;

    public HomepageInteractor(HomepageOutputBoundary homepagePresenter) {
        this.homepagePresenter = homepagePresenter;
    }

    @Override
    public void switchToSearchView() {
        homepagePresenter.switchToSearchView();
    }

    @Override
    public void switchToMyFavouritesView() {
        homepagePresenter.switchToMyFavouritesView();
    }

    @Override
    public void switchToRecommendationView() {
        homepagePresenter.switchToRecommendationView();
    }

    @Override
    public void switchToChangePasswordView() {
        homepagePresenter.switchToChangePasswordView();
    }
}