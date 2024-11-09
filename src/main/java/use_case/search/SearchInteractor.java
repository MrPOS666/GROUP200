package use_case.search;

import entity.Cocktail;
import interface_adapter.search.SearchPresenter;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface searchDataAccessObject;
    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        this.searchDataAccessObject = searchDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
    }

    @Override
    public void execute(SearchInputData searchInputData) {
        final String cocktailName = searchInputData.getCocktailName();
        if (!searchDataAccessObject.existsByName(cocktailName)) {
            searchPresenter.prepareFailView(cocktailName + ": does not exist.");
        }
        else {
        }
    }


