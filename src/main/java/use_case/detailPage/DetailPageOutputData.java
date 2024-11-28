package use_case.detailPage;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * Output Data of Detail Page Use Case.
 */
public class DetailPageOutputData {
    private String cocktailname;
    private Integer cocktailiD;
    private String instruction;
    private String photolink;
    private Map<String, String> ingredients;
    private BufferedImage image;

    public DetailPageOutputData(String cocktailname,
                                Integer cocktailiD,
                                String instruction,
                                String photolink,
                                Map<String, String> ingredients,
                                BufferedImage image) {
        this.cocktailname = cocktailname;
        this.cocktailiD = cocktailiD;
        this.instruction = instruction;
        this.photolink = photolink;
        this.ingredients = ingredients;
        this.image = image;
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
}
