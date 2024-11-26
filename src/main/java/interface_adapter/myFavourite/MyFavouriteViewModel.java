package interface_adapter.select;

import interface_adapter.ViewModel;

/**
 * The View Model for the Select View.
 */
public class SelectViewModel extends ViewModel<SelectState> {

    public SelectViewModel(String viewName) {
        super("MyFavourite");
        setState(new SelectState());
    }
}
