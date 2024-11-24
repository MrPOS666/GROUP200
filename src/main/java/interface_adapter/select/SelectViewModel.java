package interface_adapter.select;

import interface_adapter.ViewModel;

/**
 * The View Model for the Select View.
 */
public class SelectViewModel extends ViewModel<SelectState> {

    private SelectViewModel() {
        super("MyFavourite");
        setState(new SelectState());
    }
}
