package use_case.search;

import entity.Cocktail;

import java.util.List;

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


    /**
     * Executes the search use case.
     *
     * @param searchInputData the input data
     */
    @Override
    public void execute(SearchInputData searchInputData) {
        final List<String> cocktailList;
        if (searchInputData.isSearchByName()) {
            if (searchDataAccessObject.existsByName(searchInputData.getInput())) {
                final Cocktail cocktail = searchDataAccessObject.getByName(searchInputData.getInput());
                searchPresenter.prepareSuccessView(new SearchOutputData(cocktail.getCocktailName(), false));
            }
            else {
                searchPresenter.prepareFailView("Cocktail with name '" + searchInputData.getInput() + "' not found.");
            }
        }
        else if (searchInputData.isSearchById()) {
            final int cocktailId = Integer.parseInt(searchInputData.getInput());
            if (searchDataAccessObject.existsById(cocktailId)) {
                final Cocktail cocktail = searchDataAccessObject.getById(cocktailId);
                searchPresenter.prepareSuccessView(new SearchOutputData(cocktail.getCocktailName(), true));
            }
            else {
                searchPresenter.prepareFailView("Cocktail with ID '" + searchInputData.getInput() + "' not found.");
            }
        }
        else {
            searchPresenter.prepareFailView("Invalid search input. Please enter a valid name or ID.");
        }
    }
}


