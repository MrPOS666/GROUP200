package use_case.recommended;

import entity.Cocktail;
import use_case.interests.InterestsInteractor;

import java.util.*;

public class RecommendedInteractor implements RecommendedInputBoundary {
    private final RecommendedDataAccessInterfaceU recommendedDataAccessObjectU;
    private final RecommendedDataAccessInterfaceC recommendedDataAccessObjectC;
    private final RecommendedOutputBoundary recommendedPresenter;
    private final InterestsInteractor interestsInteractor;

    public RecommendedInteractor(RecommendedDataAccessInterfaceU recommendedDataAccessInterfaceU,
                                 RecommendedDataAccessInterfaceC recommendedDataAccessInterfaceC,
                                 RecommendedOutputBoundary recommendedOutputBoundary,
                                 InterestsInteractor interestsInteractor) {
        this.recommendedDataAccessObjectU = recommendedDataAccessInterfaceU;
        this.recommendedDataAccessObjectC = recommendedDataAccessInterfaceC;
        this.recommendedPresenter = recommendedOutputBoundary;
        this.interestsInteractor = interestsInteractor;
    }

    /**
     * Creates a collection of lists of cocktail attributes in order of appearance of weight
     * @param recommendedInputData
     */
    @Override
    public void execute(RecommendedInputData recommendedInputData) {

        final HashMap<String, Integer> interestsHashMap = interestsInteractor.getInterestsHashMap();

        if (interestsHashMap.isEmpty()) {
            recommendedPresenter.prepareFailView("No user interests found. Unable to generate recommendations.");
            return;
        }

        // Weighted List Fields
        final HashMap<Cocktail, Integer> weightedHashMap = new HashMap<>();
        int totalWeight = 0;

        // Go through interests, construct weightedHashMap
        for (String ingredient : interestsHashMap.keySet()) {
            int weight = interestsHashMap.get(ingredient);

            // Gets all cocktails of that ingredient
            List<Cocktail> cocktailOfThisIngredient = recommendedDataAccessObjectC.getByIngredients(Collections.singletonList(ingredient));

            for (Cocktail cocktail : cocktailOfThisIngredient) {
                if (weightedHashMap.containsKey(cocktail)) { // Just add the weight to it
                    int currentWeight = weightedHashMap.get(cocktail);
                    weightedHashMap.put(cocktail, currentWeight + weight);
                } else { // Create new with weight
                    weightedHashMap.put(cocktail, weight);
                }
                totalWeight+=weight;
            }
        }

        if (weightedHashMap.isEmpty()) {
            recommendedPresenter.prepareFailView("No recommended cocktails were available based on your interests.");
            return;
        }

        // Converts the weighted HashMap to a Cocktail List in order of weight
        final List<Cocktail> cocktails = getSortedKeysByValue(weightedHashMap);

        final List<Integer> ids = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        final List<String> instructions = new ArrayList<>();
        final List<String> photoLinks = new ArrayList<>();
        final List<Map<String, String>> ingredients = new ArrayList<>();

        for (Cocktail cocktail : cocktails) {
            ids.add(cocktail.getIdDrink());
            names.add(cocktail.getCocktailName());
            instructions.add(cocktail.getInstructions());
            photoLinks.add(cocktail.getPhotoLink());
            ingredients.add(cocktail.getIngredients());
        }
        recommendedPresenter.prepareSuccessView(new RecommendedOutputData(false, ids, names, instructions, photoLinks, ingredients));
    }

    private static List<Cocktail> getSortedKeysByValue(HashMap<Cocktail, Integer> map) {
        // Convert HashMap to List of Entry OBJs
        List<Map.Entry<Cocktail, Integer>> entryList = new ArrayList<>(map.entrySet());

        // Sort desc
        entryList.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        // Extract cocktails from sorted entries
        List<Cocktail> sortedCocktails = new ArrayList<>();
        for (Map.Entry<Cocktail, Integer> entry : entryList) {
            sortedCocktails.add(entry.getKey());
        }

        return sortedCocktails;
    }
}
