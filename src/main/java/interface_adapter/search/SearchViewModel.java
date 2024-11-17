package interface_adapter.search;

import interface_adapter.ViewModel;

/**
 * The View Model for the Search View.
 */
public class SearchViewModel extends ViewModel<SearchState> {

    public SearchViewModel(String viewName) {
        super("search");
        setState(new SearchState());
    }

}
