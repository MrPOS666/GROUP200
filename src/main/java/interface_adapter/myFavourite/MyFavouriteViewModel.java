package interface_adapter.myFavourite;

import interface_adapter.ViewModel;

/**
 * The View Model for the Select View.
 */
public class MyFavouriteViewModel extends ViewModel<MyFavouriteState> {

    public MyFavouriteViewModel(String viewName) {
        super("MyFavourite");
        setState(new MyFavouriteState());
    }
}
