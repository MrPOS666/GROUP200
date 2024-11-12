package interface_adapter.search;

import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;
import view.SearchView;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        // On success, switch to the searched cocktail in view.
        // update the search state
        final SearchState searchState = searchViewModel.getState();

        searchState.setCocktailName(searchOutputData.getCocktailName());
        this.searchViewModel.setState(searchState);

        searchViewModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String error) {
    }

}
