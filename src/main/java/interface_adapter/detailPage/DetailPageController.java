package interface_adapter.detailPage;

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
     * @param cocktailname name of current cocktail.
     * @param cocktailiD ID of current cocktail.
     * @param instruction instruction String of current cocktail
     * @param photolink photolink of current cocktail.
     * @param ingredients ingredient map of current cocktail
     */
    public void execute(String cocktailname,
                   Integer cocktailiD,
                   String instruction,
                   String photolink,
                   Map<String, String> ingredients) {
        final DetailPageInputData detailPageInputData = new DetailPageInputData(cocktailname,
                cocktailiD, instruction, photolink, ingredients);
        detailPageInteractor.execute(detailPageInputData);
    }

    /**
     * Execute the add myFavourite Use Case.
     * @param cocktailname name of current cocktail.
     * @param cocktailiD ID of current cocktail.
     * @param instruction instruction String of current cocktail
     * @param photolink photolink of current cocktail.
     * @param ingredients ingredient map of current cocktail
     */
    public void addMyFavourite(String cocktailname,
                                Integer cocktailiD,
                                String instruction,
                                String photolink,
                                Map<String, String> ingredients) {
        final DetailPageInputData detailPageInputData = new DetailPageInputData(cocktailname,
                cocktailiD, instruction, photolink, ingredients);
        detailPageInteractor.addMyFavourite(detailPageInputData);
    }
}