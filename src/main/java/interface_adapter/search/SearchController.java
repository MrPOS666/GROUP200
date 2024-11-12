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
     */
    public void execute(String cocktailName) {
        final SearchInputData inputData = new SearchInputData(cocktailName);
        searchInteractor.execute(inputData);
    }
}
