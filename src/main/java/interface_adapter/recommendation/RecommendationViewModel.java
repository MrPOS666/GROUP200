package interface_adapter.recommendation;

import interface_adapter.ViewModel;
import interface_adapter.signup.SignupState;

public class RecommendationViewModel extends ViewModel<RecommendationState> {

    public String[] getRecommendations() {
        // Simulate fetching recommendations (replace with real logic later)
        return new String[] {"Mojito", "Martini", "Margarita", "Pina Colada", "Bloody Mary", "Whiskey Sour"};
    }
}

