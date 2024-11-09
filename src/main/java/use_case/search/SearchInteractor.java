package use_case.search;

import entity.Cocktail;

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
    public void excute(SearchInputData searchInputData) {
        final String cocktailName = searchInputData.getCocktailName();
        if (!searchDataAccessObject.existsByName(cocktailName)) {
            searchPresenter.prepareFailView(cocktailName + ": does not exist.");
        }
        else {

            final Cocktail cocktail = searchDataAccessObject.get(searchInputData.getCocktailName());

            searchDataAccessObject.setCurrentCocktail(cocktail.getCocktailName());
            final SearchOutputData searchOutputData = new SearchOutputData(cocktail.getCocktailName(), false);
            searchPresenter.prepareSuccessView(searchOutputData);
        }
    }
}

