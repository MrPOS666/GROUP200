package use_case.recommended;

import entity.Cocktail;

import java.util.List;

public interface RecommendedOutputBoundary {
    void presentRecommendations(List<Cocktail> cocktails);
}
