package use_case.search_by_ingredients;

import use_case.search_by_ingredients.IngredientsInputData;

/**
 * Input Boundary for actions which are related to searching.
 */
public interface IngredientsInputBoundary {

    /**
     * Executes the search use case.
     * @param ingredientsInputData the input data
     */
    void execute(IngredientsInputData ingredientsInputData);
}
