package use_case.recommended;

import entity.Cocktail;

import java.util.List;

public interface RecommendedOutputBoundary {
    void prepareSuccessView(RecommendedOutputData outputData);

    void prepareFailView(String errorMessage);

    void switchToHomepageView();
}
