package interface_adapter.homepage;

import interface_adapter.ViewManagerModel;
import interface_adapter.change_password.LoggedInViewModel;
import interface_adapter.myFavourite.MyFavouriteState;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.recommendation.RecommendationState;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import use_case.homepage.HomepageOutputBoundary;
import use_case.homepage.HomepageOutputData;

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
    public void switchToSearchView(HomepageOutputData homepageOutputData) {
        final SearchState searchState = searchViewModel.getState();
        searchState.setUsername(homepageOutputData.getUsername());
        viewManagerModel.setState(searchViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToMyFavouritesView(HomepageOutputData homepageOutputData) {
        final MyFavouriteState myFavouriteState = myFavouriteViewModel.getState();
        myFavouriteState.setUsername(homepageOutputData.getUsername());
        viewManagerModel.setState(myFavouriteViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToRecommendationView(HomepageOutputData homepageOutputData) {
        viewManagerModel.setState(recommendationViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void switchToChangePasswordView(HomepageOutputData homepageOutputData) {
        viewManagerModel.setState(loggedInViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
