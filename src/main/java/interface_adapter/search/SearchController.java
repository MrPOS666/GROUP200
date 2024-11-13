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
     * @param cocktailName the username of the user logging in
     * @param isSearchByName the user search by name
     * @param isSearchByID the user search by ID
     * @param input the user input
     */
    public void execute(String cocktailName, boolean isSearchByName, boolean isSearchByID, String input) {
        final SearchInputData inputData = new SearchInputData(cocktailName, isSearchByName, isSearchByID, input);
        searchInteractor.execute(inputData);
    }
}
