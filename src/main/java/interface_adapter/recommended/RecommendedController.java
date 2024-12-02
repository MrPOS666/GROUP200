package interface_adapter.recommended;

import use_case.interests.InterestsInputBoundary;
import use_case.interests.InterestsInputData;
import use_case.interests.InterestsInteractor;
import use_case.recommended.RecommendedInputBoundary;
import use_case.recommended.RecommendedInputData;

import java.io.IOException;

public class RecommendedController {

    public RecommendedInputBoundary recommendedInteractor;

    public InterestsInputBoundary interestsInteractor;

    public RecommendedController(RecommendedInputBoundary recommendedInteractor,
                                 InterestsInputBoundary interestsInteractor) {
        this.recommendedInteractor = recommendedInteractor;
        this.interestsInteractor = interestsInteractor;
    }

    /**
     * Execute recommended Use Case
     */
    public void executeRecommended(String username) {
        final RecommendedInputData inputData = new RecommendedInputData(username);
        try {
            recommendedInteractor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Execute interests Use Case
     */
    public void executeInterests(String username) {
        final InterestsInputData inputData = new InterestsInputData(username);
        try {
            interestsInteractor.execute(inputData);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // TODO: Return to homepage
    public void switchToHomepageView() {
        recommendedInteractor.switchToHomepageView();
    }

    // TODO: getInfo like yuxin
}