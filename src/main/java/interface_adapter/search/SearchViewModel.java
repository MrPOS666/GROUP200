package interface_adapter.search;

import interface_adapter.ViewModel;

public class SearchViewModel extends ViewModel<SearchState> {

    public SearchViewModel(String viewName) {
        super("search");
        setState(new SearchState());
    }
}
