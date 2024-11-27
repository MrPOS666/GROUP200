package interface_adapter.detailPage;

import interface_adapter.ViewManagerModel;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.recommendation.RecommendationViewModel;
import interface_adapter.search.SearchViewModel;
import use_case.detailPage.DetailPageOutputBoundary;
import use_case.detailPage.DetailPageOutputData;

import javax.swing.text.View;

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
        detailPageState.setCocktailname(detailPageOutputData.getCocktailname());
        detailPageState.setCocktailiD(detailPageOutputData.getCocktailiD());
        detailPageState.setInstruction(detailPageOutputData.getInstruction());
        detailPageState.setPhotolink(detailPageOutputData.getPhotolink());
        detailPageState.setIngredients(detailPageOutputData.getIngredients());
        this.detailPageViewModel.setState(detailPageState);
        detailPageViewModel.firePropertyChanged();

        viewManagerModel.setState(detailPageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView() {
        System.out.println("error occur in transforming detail page view");
    }

    @Override
    public void returnOrigin(String viewModelName) {
        viewManagerModel.setState(viewModelName);
        viewManagerModel.firePropertyChanged();
    }
}
