package use_case.detailPage;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * The Input Data for the DetailPage Use Case.
 */
public class DetailPageInputData {

    private String username;
    private String cocktailName;
    private Integer cocktailID;
    private String instruction;
    private String photolink;
    private Map<String, String > ingredients;
    private BufferedImage image;
    private String previousViewName;

    public DetailPageInputData(String username,
                               String cocktailName,
                               Integer cocktailID,
                               String instruction,
                               String photolink,
                               Map<String, String > ingredients,
                               BufferedImage image,
                               String previousViewName) {
        this.username = username;
        this.cocktailName = cocktailName;
        this.cocktailID = cocktailID;
        this.instruction = instruction;
        this.photolink = photolink;
        this.ingredients = ingredients;
        this.image = image;
        this.previousViewName = previousViewName;
    }

    public String getUsername() {
        return username;
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

    public Map<String, String> getIngredients() {
        return ingredients;
    }

    public BufferedImage getImage() {
        return image;
    }

    public String getPreviousViewName() {
        return previousViewName;
    }
}
