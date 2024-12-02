package interface_adapter.detailPage;

import java.awt.image.BufferedImage;
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
    private BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);

    private String username = "";
    private String previousViewName = "";

    private String detailPageMessage = "";

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

    public void setImage(BufferedImage image) {
        this.image = image;
    }

    public void setPreviousViewName(String previousViewName) {
        this.previousViewName = previousViewName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setDetailPageMessage(String detailPageMessage) {
        this.detailPageMessage = detailPageMessage;
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

    public String getPreviousViewName() {
        return previousViewName;
    }

    public String getUsername() {
        return username;
    }

    public String getDetailPageMessage() {
        return detailPageMessage;
    }

}
