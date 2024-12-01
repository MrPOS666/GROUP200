package interface_adapter.recommended;

import use_case.recommended.RecommendedInputBoundary;
import use_case.recommended.RecommendedInputData;

import java.io.IOException;

public class RecommendedController {

    public RecommendedInputBoundary recommendedInteractor;

    public RecommendedController(RecommendedInputBoundary recommendedInteractor) {
        this.recommendedInteractor = recommendedInteractor;
    }

    /**
     * Execute recommendated Use Case
      */
    public void execute() throws IOException {
        final RecommendedInputData inputData = new RecommendedInputData();
        recommendedInteractor.execute();
    }
}
