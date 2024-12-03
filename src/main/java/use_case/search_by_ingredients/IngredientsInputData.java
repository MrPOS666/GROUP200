package use_case.search_by_ingredients;

import java.util.List;

/**
 * Data structure for search input parameters in the Search Use Case.
 */
public class IngredientsInputData {

    private final List<String> input;

    /**
     * Constructor for search by ingredients input, which is a list of ingredients.
     * @param input the user input to search by
     */
    public IngredientsInputData(List<String> input) {
        this.input = input;
    }

    /**
     * Checks if the ingredients list is empty.
     * @return true if ingredients is not empty; false otherwise
     */
    public boolean hasIngredients() {
        return input != null;
    }

    /**
     * Checks if the search is by cocktail name.
     * A name usually contains letters, so we check for at least one letter.
     * @return true if searching by name; false otherwise
     */

    // public boolean isSearchByName() {
        // return input != null && input.matches(".*[a-zA-Z].*");
    // }

    /**
     * Checks if the search is by cocktail ID.
     * An ID is a number, so we check if it only contains digits.
     * @return true if searching by ID; false otherwise
     */
    // public boolean isSearchById() {
        // return input != null && input.matches("\\d+");
    // }

    /**
     * Gets the input provided by the user, which can be a cocktail name or ID.
     * @return the input string
     */
    public List<String> getInput() {
        return input;
    }
}