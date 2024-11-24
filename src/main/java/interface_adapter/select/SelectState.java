package interface_adapter.select;

import java.util.List;

/**
 * State for select.
 */
public class SelectState {

    private String username; // The username of the current user
    private List<Integer> selectedCocktails; // List of selected cocktail IDs
    private List<Integer> cocktailList; // List of all available cocktail IDs
    private String selectError; // Error message, if any

    /**
     * Gets the username of the current user.
     *
     * @return the username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the current user.
     *
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the list of selected cocktail IDs.
     *
     * @return the list of selected cocktail IDs.
     */
    public List<Integer> getSelectedCocktails() {
        return selectedCocktails;
    }

    /**
     * Sets the list of selected cocktail IDs.
     *
     * @param selectedCocktails the list of selected cocktail IDs.
     */
    public void setSelectedCocktails(List<Integer> selectedCocktails) {
        this.selectedCocktails = selectedCocktails;
    }

    /**
     * Gets the list of all available cocktail IDs.
     *
     * @return the list of all cocktail IDs.
     */
    public List<Integer> getCocktailList() {
        return cocktailList;
    }

    /**
     * Gets the error message for selection.
     *
     * @param errorMessage the error message.
     */
    public void setSelectError(String errorMessage) {
        this.selectError = errorMessage;
    }
}
