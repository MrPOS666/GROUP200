package use_case.detailPage;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Output Data of Detail Page Use Case.
 */
public class DetailPageOutputData {
    private String username;
    private String cocktailname;
    private Integer cocktailiD;
    private String instruction;
    private String photolink;
    private Map<String, String> ingredients;
    private BufferedImage image;
    private String previoueViewName;

    public DetailPageOutputData(String username,
                                String cocktailname,
                                Integer cocktailiD,
                                String instruction,
                                String photolink,
                                Map<String, String> ingredients,
                                BufferedImage image,
                                String previoueViewName) {
        this.username = username;
        this.cocktailname = cocktailname;
        this.cocktailiD = cocktailiD;
        this.instruction = instruction;
        this.photolink = photolink;
        this.ingredients = ingredients;
        this.image = image;
        this.previoueViewName = previoueViewName;
    }

    public String getUsername() {
        return username;
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

    public BufferedImage getImage() {
        return image;
    }

    public String getPrevioueViewName() {
        return previoueViewName;
    }
}
