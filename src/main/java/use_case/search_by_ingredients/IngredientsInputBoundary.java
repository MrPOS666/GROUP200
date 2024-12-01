package use_case.search_by_ingredients;

import use_case.search_by_ingredients.IngredientsInputData;

import java.util.List;

/**
 * Input Boundary for actions which are related to searching.
 */
public interface IngredientsInputBoundary {

    /**
     * Executes the search use case.
     * @param ingredientsInputData the input data
     */
    void execute(IngredientsInputData ingredientsInputData);

    /**
     * return full information in a list.
     * @param ingredientsInputData the input data
     * @return list of info
     */
    List<Object> getInfo(IngredientsInputData ingredientsInputData);
}
