package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.result.ResultState;
import interface_adapter.result.ResultViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;

/**
 * The Presenter for the Search Use Case.
 */
public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ResultViewModel resultViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel searchViewModel, ResultViewModel resultViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.resultViewModel = resultViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData response) {
        // On success, switch to the result in view.

        final ResultState resultState = resultViewModel.getState();
        ResultState.setCocktailName(response.getCocktailName());
        this.resultViewModel.setState(resultState);
        this.resultViewModel.firePropertyChanged();

        this.viewManagerModel.setState(ResultViewModel.giveViewName());
        this.viewManagerModel.firePropertyChanged();

    }

    @Override
    public void prepareFailView(String errorMessage) {
        final SearchState searchState = searchViewModel.getState();
        searchState.setSearchError(errorMessage);
        searchViewModel.firePropertyChanged();
    }
}
