package use_case.get_recommendations;

import entity.Cocktail;
import entity.User;

import java.util.List;

public interface get_recommendationsInputBoundary {
    List<Cocktail> getRecommendations(User user);
}
