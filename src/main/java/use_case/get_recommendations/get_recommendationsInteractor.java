package use_case.get_recommendations;

import data_access.DBUserDataAccessObject;
import entity.Cocktail;
import entity.User;

import java.util.*;

public class get_recommendationsInteractor {

    private final get_recommendationsUserDataAccessInterface userDataAccess; // DAL for API and User
    private final get_recommendationsOutputBoundary outputBoundary;

    public get_recommendationsInteractor(get_recommendationsUserDataAccessInterface userDataAccess,
                                    get_recommendationsOutputBoundary outputBoundary) {
        this.userDataAccess = userDataAccess;
        this.outputBoundary = outputBoundary;
    }

    public List<Cocktail> getRecommendations(User user) {
        HashMap<String, Integer> interests = userDataAccess.getMyInterests(user);
        List<String> selectedTags = selectTags(interests);

        Set<Cocktail> uniqueCocktails = new HashSet<>();
        for (String tag : selectedTags) {
            List<Cocktail> cocktailsForTag = userDataAccess.getDrinksByTag(tag);
            Cocktail randomCocktail = getRandomCocktail(cocktailsForTag, uniqueCocktails);
            if (randomCocktail != null) {
                uniqueCocktails.add(randomCocktail);
            }
        }

        while (uniqueCocktails.size() < 6) {
            String tag = selectedTags.get(new Random().nextInt(selectedTags.size()));
            List<Cocktail> cocktailsForTag = userDataAccess.getDrinksByTag(tag);
            Cocktail randomCocktail = getRandomCocktail(cocktailsForTag, uniqueCocktails);
            if (randomCocktail != null) {
                uniqueCocktails.add(randomCocktail);
            }
        }

        List<Cocktail> cocktails = new ArrayList<>(uniqueCocktails);
        outputBoundary.presentRecommendations(cocktails);
        return cocktails;
    }

    private List<String> selectTags(HashMap<String,Integer> interests) {
        List<String> selectedTags = new ArrayList<>();
        Random rand = new Random();

        for (int i = 0; i<6; i++) {
            String selectedTag = selectWeightedTag(interests, rand);
            selectedTags.add(selectedTag);
        }
        return selectedTags;
    }

    private String selectWeightedTag(HashMap<String, Integer> interests, Random rand) {
        int totalWeight = interests.values().stream().mapToInt(Integer::intValue).sum();
        int randomWeight = rand.nextInt(totalWeight);

        int cumulativeWeight = 0;
        for (Map.Entry<String, Integer> entry : interests.entrySet()) {
            cumulativeWeight += entry.getValue();
            if (randomWeight < cumulativeWeight) {
                return entry.getKey();
            }
        }
        return null;
    }

    private Cocktail getRandomCocktail(List<Cocktail> cocktails, Set<Cocktail> uniqueCocktails) {
        Random rand = new Random();
        if (cocktails.isEmpty()) {
            return null;
        }

        Collections.shuffle(cocktails);
        for (Cocktail cocktail : cocktails) {
            if (!uniqueCocktails.contains(cocktail)) {
                return cocktail;
            }
        }
        return null;
    }
}
