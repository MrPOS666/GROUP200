package interface_adapter.search;

import entity.Cocktail;
import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

import java.util.ArrayList;
import java.util.List;

public class SearchController {

    private SearchInputBoundary searchInteractor;

    public SearchController(SearchInputBoundary searchInteractor) {
        this.searchInteractor = searchInteractor;
    }

    /**
     * Executes the search Use Case.
     * @param input the user input
     */
    public void execute(String input) {
        final SearchInputData inputData = new SearchInputData(input);
        searchInteractor.execute(inputData);
    }
}
