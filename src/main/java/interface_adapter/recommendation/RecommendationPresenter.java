package interface_adapter.recommendation;

import view.recommendedView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RecommendationPresenter {

    private final recommendedView view;
    private final RecommendationViewModel viewModel;

    public RecommendationPresenter(recommendedView view, RecommendationViewModel viewModel) {
        this.view = view;
        this.viewModel = viewModel;

        // Set listeners for buttons
        this.view.setRefreshButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshRecommendations();
            }
        });

        this.view.setBackButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                navigateBack();
            }
        });
    }

    private void refreshRecommendations() {
        // Fetch recommendations from the ViewModel (this could be a static or dynamic method)
        String[] recommendations = viewModel.getRecommendations();
        // Update the view with the fetched recommendations
        view.updateDrinkButtons(recommendations);
    }

    private void navigateBack() {
        // Handle back navigation, e.g., change to a different view
        System.out.println("Going back...");
    }
}
