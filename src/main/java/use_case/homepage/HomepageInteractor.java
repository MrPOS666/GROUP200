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
    public void switchToSearchView(HomepageInputData input) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData(input.getUsername());
        homepagePresenter.switchToSearchView(homepageOutputData);
    }

    @Override
    public void switchToMyFavouritesView(HomepageInputData input) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData(input.getUsername());
        homepagePresenter.switchToMyFavouritesView(homepageOutputData);
    }

    @Override
    public void switchToRecommendationView(HomepageInputData input) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData(input.getUsername());
        homepagePresenter.switchToRecommendationView(homepageOutputData);
    }

    @Override
    public void switchToChangePasswordView(HomepageInputData input) {
        final HomepageOutputData homepageOutputData = new HomepageOutputData(input.getUsername());
        homepagePresenter.switchToChangePasswordView(homepageOutputData);
    }
}
