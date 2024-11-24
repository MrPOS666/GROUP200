package use_case.search_by_ingredients;

import use_case.search_by_ingredients.IngredientsOutputData;

/**
 * The output boundary for the Search_By_Ingredients Use Case.
 */
public interface IngredientsOutputBoundary {
    /**
     * Prepares the success view for the Search Case.
     * @param outputData the output data
     */
    void prepareSuccessView(IngredientsOutputData outputData);

    /**
     * Prepares the failure view for the Search Use Case.
     * @param errorMessage the explanation of the failure
     */
    void prepareFailView(String errorMessage);
}
