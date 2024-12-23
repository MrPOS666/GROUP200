package interface_adapter.detailPage;

import java.awt.image.BufferedImage;
import java.util.Map;

import use_case.detailPage.DetailPageInputBoundary;
import use_case.detailPage.DetailPageInputData;

/**
 * Controller of DetailPage Use Case.
 */
public class DetailPageController {

    private final DetailPageInputBoundary detailPageInteractor;

    public DetailPageController(DetailPageInputBoundary detailPageInteractor) {
        this.detailPageInteractor = detailPageInteractor;
    }

    /**
     * Execute the DetailPage Use Case.
     * @param username name of current user.
     * @param cocktailname name of current cocktail.
     * @param cocktailiD ID of current cocktail.
     * @param instruction instruction String of current cocktail
     * @param photolink photolink of current cocktail.
     * @param ingredients ingredient map of current cocktail.
     * @param image image of current cocktail.
     * @param previousViewName name of previous View.
     */
    public void execute(String username,
                        String cocktailname,
                        Integer cocktailiD,
                        String instruction,
                        String photolink,
                        Map<String, String> ingredients,
                        BufferedImage image,
                        String previousViewName) {
        final DetailPageInputData detailPageInputData = new DetailPageInputData(username,
                                                                                cocktailname,
                                                                                cocktailiD,
                                                                                instruction,
                                                                                photolink,
                                                                                ingredients,
                                                                                image,
                                                                                previousViewName);
        detailPageInteractor.execute(detailPageInputData);
    }

    /**
     * Execute the add myFavourite Use Case.
     *
     * @param username name of current user
     * @param cocktailname name of current cocktail.
     * @param cocktailiD   ID of current cocktail.
     * @param instruction  instruction String of current cocktail
     * @param photolink    photolink of current cocktail.
     * @param ingredients  ingredient map of current cocktail
     * @param image        image of current cocktail
     * @param previousViewName name of the previous View
     */
    public void addMyFavourite(String username,
                               String cocktailname,
                               Integer cocktailiD,
                               String instruction,
                               String photolink,
                               Map<String, String> ingredients,
                               BufferedImage image,
                               String previousViewName) {
        final DetailPageInputData detailPageInputData = new DetailPageInputData(username, cocktailname,
                cocktailiD, instruction, photolink, ingredients, image, previousViewName);
        detailPageInteractor.addMyFavourite(detailPageInputData);
    }

    /**
     * Execute the returnOrigin Use case.
     * @param previousViewName name of the previousViewName.
     */
    public void returnOrigin(String previousViewName) {
        detailPageInteractor.returnOrigin(previousViewName);
    }
}
