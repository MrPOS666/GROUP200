package interface_adapter.recommendation;

import view.recommendedView;
import javax.swing.*;

public class RecommendationController {

    public void start() {
        // Create instances of the View, ViewModel, and Presenter
        recommendedView view = new recommendedView();
        RecommendationViewModel viewModel = new RecommendationViewModel();
        RecommendationPresenter presenter = new RecommendationPresenter(view, viewModel);

        // Set up the window
        JFrame frame = new JFrame("Recommended Drinks");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.add(view);
        frame.setVisible(true);
    }
}
