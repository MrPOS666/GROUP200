package interface_adapter.detailPage;

import interface_adapter.ViewManagerModel;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.search.SearchViewModel;
import use_case.detailPage.DetailPageOutputBoundary;
import use_case.detailPage.DetailPageOutputData;

/**
 * The Presenter for the Detail Page Presenter.
 */
public class DetailPagePresenter implements DetailPageOutputBoundary {

    private final ViewManagerModel viewManagerModel;
    private final DetailPageViewModel detailPageViewModel;
    private final SearchViewModel searchViewModel;
    private final MyFavouriteViewModel myFavouriteViewModel;
    private final RecommendationViewModel recommendationViewModel;

    public DetailPagePresenter(ViewManagerModel viewManagerModel,
                               DetailPageViewModel detailPageViewModel,
                               SearchViewModel searchViewModel,
                               MyFavouriteViewModel myFavouriteViewModel,
                               RecommendationViewModel recommendationViewModel) {
        this.viewManagerModel = viewManagerModel;
        this.detailPageViewModel = detailPageViewModel;
        this.searchViewModel = searchViewModel;
        this.myFavouriteViewModel = myFavouriteViewModel;
        this.recommendationViewModel = recommendationViewModel;
    }

    @Override
    public void prepareSuccessView(DetailPageOutputData detailPageOutputData) {
        // On success, switch to the Detail View
        final DetailPageState detailPageState = detailPageViewModel.getState();

        detailPageState.setUsername(detailPageOutputData.getUsername());
        detailPageState.setCocktailname(detailPageOutputData.getCocktailname());
        detailPageState.setCocktailiD(detailPageOutputData.getCocktailiD());
        detailPageState.setInstruction(detailPageOutputData.getInstruction());
        detailPageState.setPhotolink(detailPageOutputData.getPhotolink());
        detailPageState.setIngredients(detailPageOutputData.getIngredients());
        detailPageState.setImage(detailPageOutputData.getImage());
        detailPageState.setPreviousViewName(detailPageOutputData.getPrevioueViewName());
        detailPageState.setDetailPageMessage(detailPageOutputData.getMessage());
        this.detailPageViewModel.setState(detailPageState);

        detailPageViewModel.firePropertyChanged();

        viewManagerModel.setState(detailPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(DetailPageOutputData detailPageOutputData) {
        final DetailPageState detailPageState = detailPageViewModel.getState();
        detailPageState.setDetailPageMessage(detailPageOutputData.getMessage());
        detailPageViewModel.setState(detailPageState);

        detailPageViewModel.firePropertyChanged();

        viewManagerModel.setState(detailPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void returnOrigin(String viewModelName) {
        viewManagerModel.setState(viewModelName);
        viewManagerModel.firePropertyChanged();
    }
}
