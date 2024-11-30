package interface_adapter.search_by_ingredients;

import entity.Cocktail;
import use_case.search_by_ingredients.IngredientsInputBoundary;
import use_case.search_by_ingredients.IngredientsInputData;
import java.util.ArrayList;
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

    /**
     * A helper method to return full cocktail information.
     * @param id input id
     * @return a list consists of cocktail information
     */
    public List<Object> getInfo(int id) {
        final IngredientsInputData inputData = new IngredientsInputData(id);
        return this.ingredientsInteractor.getInfo(inputData);
    }
}
