package interface_adapter.search;

import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;
import view.SearchView;

public class SearchPresenter implements SearchOutputBoundary {

    private SearchViewModel searchViewModel;

    public SearchPresenter(SearchViewModel searchViewModel) {
        this.searchViewModel = searchViewModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        // On success, switch to the searched cocktail in view.

    }

    @Override
    public void prepareFailView(String errorMessage) {

    }

}
