package interface_adapter.recommended;

import interface_adapter.ViewModel;

public class RecommendedViewModel extends ViewModel<RecommendedState> {

    public RecommendedViewModel(String viewName) {
        super("recommended");
        setState(new RecommendedState());
    }
}

