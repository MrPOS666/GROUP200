package interface_adapter.detailPage;

import interface_adapter.ViewModel;

/**
 * The ViewModel for the DetailPage View.
 */
public class DetailPageViewModel extends ViewModel<DetailPageState> {
    public DetailPageViewModel() {
        super("detailpage");
        setState(new DetailPageState());
    }
}
