package interface_adapter.recommended;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class RecommendedState {

    private String username;
    private String error; // Error message, if any


    private List<Integer> idList = new ArrayList<>();
    private List<String> cocktailNamesList = new ArrayList<>();
    private List<Map<String, String>> ingredientsList = new ArrayList<>();
    private List<String> instructionList = new ArrayList<>();
    private List<String> photoLinkList = new ArrayList<>();
    private List<BufferedImage> imageList = new ArrayList<>();

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public void setImageList(List<BufferedImage> images) {
        this.imageList = images;
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

    public List<BufferedImage> getImageList() {
        return imageList;
    }

    public void setRecommendedError(String errorMessage) {
        this.error = errorMessage;
    }

    public String getRecommendedError() {
        return error;
    }
}
