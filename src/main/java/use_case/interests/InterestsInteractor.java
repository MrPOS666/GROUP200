package use_case.interests;

import entity.Cocktail;

import java.util.HashMap;
import java.util.List;

public class InterestsInteractor implements InterestsInputBoundary {
    private final InterestsDataAccessInterface interestsDataAccessObject;
    private final HashMap<String, Integer> interestsHashMap = new HashMap<>();

    public InterestsInteractor(InterestsDataAccessInterface interestsDataAccessObject) {
        this.interestsDataAccessObject = interestsDataAccessObject;
    }

    @Override
    public void execute(InterestsInputData interestsInputData) {
        // Get favourites
        final List<Cocktail> favourites = interestsDataAccessObject.getUser(interestsInputData.getUsername()).getMyFavourite();

        for (Cocktail cocktail : favourites) {
            for (String ingredient : cocktail.getIngredients().keySet()) {
                if (interestsHashMap.containsKey(ingredient)) { // +1 counter if ingredient in hash
                    Integer value = interestsHashMap.get(ingredient) + 1;
                    interestsHashMap.put(ingredient, value);
                } else { // add to hashmap and set 1
                    interestsHashMap.put(ingredient, 1);
                }
            }
        }
    }

    public HashMap<String, Integer> getInterestsHashMap() {
        return new HashMap<>(interestsHashMap);
    }
}
