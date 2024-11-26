package interface_adapter.myFavourite;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class MyFavouriteViewModel extends ViewModel<MyFavouriteState> {

    public MyFavouriteViewModel() {
        super("My Favourite");
        setState(new MyFavouriteState());
    }

}