package use_case.get_recommendations;

import entity.Cocktail;
import entity.User;

import java.util.HashMap;
import java.util.List;

public interface get_recommendationsUserDataAccessInterface{
    HashMap<String, Integer> getMyInterests(User user);

    List<Cocktail> getDrinksByTag(String tag);
}
