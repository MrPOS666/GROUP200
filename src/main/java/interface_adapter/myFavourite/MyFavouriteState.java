package interface_adapter.myFavourite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * State for select.
 */
public class MyFavouriteState {

    private String username; // The username of the current user
    private List<Integer> selectedCocktails; // List of selected cocktail IDs
    private String selectError; // Error message, if any
    private List<Integer> idList;
    private List<Map<String, String>> ingredientsList;
    private List<String> instructionList;
    private List<String> cocktailNamesList = new ArrayList<>();
    private List<String> photoLinkList;

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
     * Gets the error message for selection.
     *
     * @param errorMessage the error message.
     */
    public void setSelectError(String errorMessage) {
        this.selectError = errorMessage;
    }

    public void setCocktailNamesList(List<String> names) {
        this.cocktailNamesList = names;
    }

    public void setIdList(List<Integer> ids) {
        this.idList = ids;
    }

    public void setIngredientsList(List<Map<String, String>> ingredients) {
        this.ingredientsList = ingredients;
    }

    public void setInstructionList(List<String> instructions) {
        this.instructionList = instructions;
    }

    public void setPhotoLinkList(List<String> photoLinks) {
        this.photoLinkList = photoLinks;
    }

    public String getSelectError() {
        return selectError;
    }

    public List<String> getCocktailNamesList() {
        return cocktailNamesList;
    }

    public List<Integer> getIdList() {
        return idList;
    }

    public List<Map<String, String>> getIngredientsList() {
        return ingredientsList;
    }

    public List<String> getInstructionList() {
        return instructionList;
    }

    public List<String> getPhotoLinkList() {
        return photoLinkList;
    }
}
