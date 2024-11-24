package interface_adapter.search_by_ingredients;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;

/**
 * The View Model for the Search View.
 */
public class IngredientsViewModel extends ViewModel<SearchState> {

    public IngredientsViewModel(String viewName) {
        super("search");
        setState(new SearchState());
    }

}
