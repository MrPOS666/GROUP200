package interface_adapter.search_by_ingredients;

import use_case.search.SearchInputBoundary;
import use_case.search.SearchInputData;

public class IngredientsController {

    private SearchInputBoundary searchInteractor;

    public IngredientsController(SearchInputBoundary searchInteractor) {
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
