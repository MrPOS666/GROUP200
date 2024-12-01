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
        final List<Cocktail> favourites = interestsDataAccessObject.getUserFavourites(interestsInputData.getUser());

        for (Cocktail cocktail : favourites) {
            List<String> tags = interestsDataAccessObject.getCocktailTags(cocktail);

            for (String tag : tags) {
                if (interestsHashMap.containsKey(tag)) { // +1 value if key already exists
                    Integer value = interestsHashMap.get(tag) + 1;
                    interestsHashMap.put(tag, value);
                } else { // Add new key with value 1
                    interestsHashMap.put(tag, 1);
                }
            }
        }
    }

    public HashMap<String, Integer> getInterestsMap() {
        return new HashMap<>(interestsHashMap);
    }
}
