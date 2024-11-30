package use_case.search_by_ingredients;

import entity.Cocktail;
import use_case.search.SearchOutputBoundary;
import use_case.search_by_ingredients.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The Search Interactor.
 */
public class IngredientsInteractor implements IngredientsInputBoundary {
    private final IngredientsDataAccessInterface ingredientsDataAccessObject;
    private final IngredientsOutputBoundary ingredientsPresenter;

    public IngredientsInteractor(IngredientsDataAccessInterface ingredientsDataAccessInterface,
                                  IngredientsOutputBoundary ingredientsOutputBoundary) {
        this.ingredientsDataAccessObject = ingredientsDataAccessInterface;
        this.ingredientsPresenter = ingredientsOutputBoundary;
    }

    /**
     * Executes the search use case.
     *
     * @param ingredientsInputData the input data
     */
    @Override
    public void execute(IngredientsInputData ingredientsInputData) {
        if (ingredientsInputData.hasIngredients()) {
            if (ingredientsDataAccessObject.existsByIngredients(ingredientsInputData.getInput())) {
                final List<Cocktail> cocktails = ingredientsDataAccessObject.getByIngredients(ingredientsInputData.getInput());
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
                ingredientsPresenter.prepareSuccessView(new IngredientsOutputData(false, ids, names, instructions, photoLinks, ingredients));
            }
            else {
                ingredientsPresenter.prepareFailView("Unable to find any cocktails with given ingredients");
            }
        }
        else {
            ingredientsPresenter.prepareFailView("Invalid search input. Please enter valid ingredients.");
        }
    }

    /**
     * A helper method to return full cocktail information.
     * @param ingredientsInputData the input data
     * @return a list consists of cocktail information
     */
    public List<Object> getInfo(IngredientsInputData ingredientsInputData) {
        final int id = ingredientsInputData.getId();
        final Cocktail cocktail = ingredientsDataAccessObject.getById(id);
        final List<Object> info = new ArrayList<>();
        info.add(cocktail.getCocktailName());
        info.add(cocktail.getIdDrink());
        info.add(cocktail.getIngredients());
        info.add(cocktail.getInstructions());
        info.add(cocktail.getPhotoLink());
        return info;
    }
}



