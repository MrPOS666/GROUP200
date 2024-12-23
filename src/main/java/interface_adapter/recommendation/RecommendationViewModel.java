package interface_adapter.recommendation;

import interface_adapter.ViewModel;

/**
 * The View Model for the Login View.
 */
public class RecommendationViewModel extends ViewModel<RecommendationState> {

    public RecommendationViewModel() {
        super("Recommendation");
        setState(new RecommendationState());
    }

}
