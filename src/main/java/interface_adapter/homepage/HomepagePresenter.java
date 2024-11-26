package interface_adapter.homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.search.SearchViewModel;
import use_case.homepage.HomepageOutputBoundary;

/**
 * The Presenter for the Homepage us case.
 */
public class HomepagePresenter implements HomepageOutputBoundary {

    private final MyFavouriteViewModel myFavouriteViewModel;
    private final RecommendationViewModel recommendationViewModel;
    private final SearchViewModel searchViewModel;
    private final LoggedInViewModel loggedInViewModel;
    private final ViewManagerModel viewManagerModel;

    public HomepagePresenter(ViewManagerModel viewManagerModel,
                             RecommendationViewModel recommendationViewModel,
                             MyFavouriteViewModel myFavouriteViewModel,
                             SearchViewModel searchViewModel,
                             LoggedInViewModel loggedInViewModel) {
        this.recommendationViewModel = recommendationViewModel;
        this.myFavouriteViewModel = myFavouriteViewModel;
        this.searchViewModel = searchViewModel;
        this.loggedInViewModel = loggedInViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void switchToSearchView() {
        viewManagerModel.setState(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMyFavouritesView() {
        viewManagerModel.setState(myFavouriteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecommendationView() {
        viewManagerModel.setState(recommendationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView() {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();

    }
}