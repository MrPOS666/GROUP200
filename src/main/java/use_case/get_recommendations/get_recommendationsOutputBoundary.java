package use_case.get_recommendations;

import entity.Cocktail;

import java.util.List;

public interface get_recommendationsOutputBoundary {
    void presentRecommendations(List<Cocktail> cocktails);
}
