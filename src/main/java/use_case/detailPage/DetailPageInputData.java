package use_case.detailPage;

import java.util.Map;

/**
 * The Input Data for the DetailPage Use Case.
 */
public class DetailPageInputData {

    private String cocktailName;
    private Integer cocktailID;
    private String instruction;
    private String photolink;
    private Map<String, String > ingredient;

    public DetailPageInputData(String cocktailName,
                               Integer cocktailID,
                               String instruction,
                               String photolink,
                               Map<String, String > ingredient) {
        this.cocktailName = cocktailName;
        this.cocktailID = cocktailID;
        this.instruction = instruction;
        this.photolink = photolink;
        this.ingredient = ingredient;
    }

    public String getCocktailName() {
        return cocktailName;
    }

    public Integer getCocktailID() {
        return cocktailID;
    }

    public String getInstruction() {
        return instruction;
    }

    public String getPhotolink() {
        return photolink;
    }

    public Map<String, String> getIngredient() {
        return ingredient;
    }
}
