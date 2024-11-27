package interface_adapter.search;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class SearchController {

    private SearchInputBoundary searchInteractor;

    public SearchController(SearchInputBoundary searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    /**
     * Executes the search Use Case.
     * @param cocktailName cocktail name
     * @param isSearchByName if search by name
     * @param isSearchById if search by id
     * @param input the user input
     */
    public void execute(String cocktailName, boolean isSearchByName, boolean isSearchById, String input) {
        final SearchInputData inputData = new SearchInputData(cocktailName, isSearchByName, isSearchById, input);
        searchInteractor.execute(inputData);
    }
}
