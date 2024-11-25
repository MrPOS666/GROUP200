package interface_adapter.search_by_ingredients;

import use_case.search_by_ingredients.IngredientsInputBoundary;
import use_case.search_by_ingredients.IngredientsInputData;

import java.util.List;

public class IngredientsController {

    private IngredientsInputBoundary ingredientsInteractor;

    public IngredientsController(IngredientsInputBoundary ingredientsInteractor) {
        this.ingredientsInteractor = ingredientsInteractor;
    }

    /**
     * Executes the search Use Case.
     * @param input the user input
     */
    public void execute(List<String> input) {
        final IngredientsInputData inputData = new IngredientsInputData(input);
        ingredientsInteractor.execute(inputData);
    }
}
