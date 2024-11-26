package interface_adapter.homepage;

import interface_adapter.ViewModel;

/**
 * The View Model for the Homepage View.
 */
public class HomepageViewModel extends ViewModel<HomepageState> {

    public HomepageViewModel() {
        super("homepage");
        setState(new HomepageState());
    }
}
