package interface_adapter.detailPage;

import java.util.HashMap;
import java.util.Map;

/**
 * State of DetailPage.
 */
public class DetailPageState {
    private String cocktailname = "";
    private Integer cocktailiD = 0;
    private String instruction = "";
    private String photolink = "";
    private Map<String, String> ingredients = new HashMap<>();

    public void setCocktailname(String cocktailname) {
        this.cocktailname = cocktailname;
    }

    public void setCocktailiD(Integer cocktailiD) {
        this.cocktailiD = cocktailiD;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public void setPhotolink(String photolink) {
        this.photolink = photolink;
    }

    public void setIngredients(Map<String, String> ingredients) {
        this.ingredients = ingredients;
    }

    public String getCocktailname() {
        return cocktailname;
    }

    public Integer getCocktailiD() {
        return cocktailiD;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getPhotolink() {
        return photolink;
    }

    public Map<String, String> getIngredients() {
        return ingredients;
    }
}