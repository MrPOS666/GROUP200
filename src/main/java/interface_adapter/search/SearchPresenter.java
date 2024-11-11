package interface_adapter.search;

import interface_adapter.ViewManagerModel;
import interface_adapter.back.ResultState;
import interface_adapter.back.ResultViewModel;
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
        // On success, update the search view model with cocktail details
        final SearchState searchState = searchViewModel.getState();
        searchState.setCocktailName(response.getCocktailName());
        searchState.setCocktailDetails(response.getCocktailDetails()); // Assuming response includes more details
        searchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // On failure, set the error message in the search state
        final SearchState searchState = searchViewModel.getState();
        searchState.setSearchError(errorMessage);
        searchViewModel.firePropertyChanged();
    }
}
