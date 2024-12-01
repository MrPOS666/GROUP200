package use_case.search_by_ingredients;

import java.util.List;

/**
 * Data structure for search input parameters in the Search Use Case.
 */
public class IngredientsInputData {
    private List<String> input;
    private int id;

    /**
     * Constructor for search by ingredients input, which is a list of ingredients.
     * @param input the user input to search by
     */
    public IngredientsInputData(List<String> input) {
        this.input = input;
    }

    /**
     * Constructor for search by ingredients input, which is a list of ingredients.
     * @param id the id of cocktail
     */
    public IngredientsInputData(int id) {
        this.id = id;
    }

    /**
     * Checks if the ingredients list is empty.
     * @return true if ingredients is not empty; false otherwise
     */
    public boolean hasIngredients() {
        return input != null;
    }

    public int getId() {
        return this.id;
    }

    /**
     * Gets the input provided by the user, which can be a cocktail name or ID.
     * @return the input string
     */
    public List<String> getInput() {
        return input;
    }
}