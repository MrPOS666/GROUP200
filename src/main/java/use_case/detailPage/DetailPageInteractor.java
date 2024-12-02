package use_case.detailPage;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

import entity.Cocktail;
import entity.CocktailFactory;
import entity.User;

/**
 * The Detail Page Interactor.
 */
public class DetailPageInteractor implements DetailPageInputBoundary {

    private DetailPageOutputBoundary detailPageOutputPresenter;
    private DetailPageDataAccessInterface detailPageDataAccessObject;
    private CocktailFactory cocktailFactory;

    public DetailPageInteractor(DetailPageOutputBoundary detailPageOutputPresenter,
                                DetailPageDataAccessInterface detailPageDataAccessObject,
                                CocktailFactory cocktailFactory) {
        this.detailPageOutputPresenter = detailPageOutputPresenter;
        this.detailPageDataAccessObject = detailPageDataAccessObject;
        this.cocktailFactory = cocktailFactory;
    }

    @Override
    public void execute(DetailPageInputData detailPageInputData) {
        try {
            // Extract details from input data
            final String username = detailPageInputData.getUsername();
            final String cocktailName = detailPageInputData.getCocktailName();
            final Integer cocktailID = detailPageInputData.getCocktailID();
            final String instruction = detailPageInputData.getInstruction();
            final String photolink = detailPageInputData.getPhotolink();
            final Map<String, String> ingredients = detailPageInputData.getIngredients();
            final BufferedImage image = detailPageInputData.getImage();
            final String previousViewName = detailPageInputData.getPreviousViewName();

            // Create output data
            final DetailPageOutputData outputData = new DetailPageOutputData(false,
                    username,
                    cocktailName,
                    cocktailID,
                    instruction,
                    photolink,
                    ingredients,
                    image,
                    previousViewName);

            // Send to presenter to prepare the success view
            detailPageOutputPresenter.prepareSuccessView(outputData);
        }
        catch (DetailPageDataAccessException exception) {
            // Handle any unexpected errors
            detailPageOutputPresenter.prepareFailView(new DetailPageOutputData(true, "DAO error"));
        }
    }

    @Override
    public void addMyFavourite(DetailPageInputData detailPageInputData) {
        try {
            // 1. Load the current user by username
            final User user = detailPageDataAccessObject.getUser(detailPageInputData.getUsername());

            // 2. Load the user's current favorite cocktail list
            final List<Cocktail> favoriteCocktails = user.getMyFavourite();

            // 3. Create a new Cocktail object from input data
            final Cocktail newCocktail = cocktailFactory.create(detailPageInputData.getCocktailID(),
                                                                detailPageInputData.getCocktailName(),
                                                                detailPageInputData.getInstruction(),
                                                                detailPageInputData.getPhotolink(),
                                                                detailPageInputData.getIngredients(),
                                                                detailPageInputData.getImage());
            // 3.b. Check if the cocktail exist in the list
            boolean cocktailExists = false;
            for (Cocktail cocktail : favoriteCocktails) {
                if (cocktail.getIdDrink() == newCocktail.getIdDrink()) {
                    cocktailExists = true;
                    break;
                }
            }
            // 4. Add the new cocktail to the user's favorite list
            if (!cocktailExists) {
                favoriteCocktails.add(newCocktail);

                // 5. Update the user in the data layer
                detailPageDataAccessObject.addMyFavourite(user, favoriteCocktails);

                final DetailPageOutputData outputData = new DetailPageOutputData(false,
                        detailPageInputData.getUsername(),
                        detailPageInputData.getCocktailName(),
                        detailPageInputData.getCocktailID(),
                        detailPageInputData.getInstruction(),
                        detailPageInputData.getPhotolink(),
                        detailPageInputData.getIngredients(),
                        detailPageInputData.getImage(),
                        detailPageInputData.getPreviousViewName());

                outputData.setMessage("Successfully added my favourite");

                detailPageOutputPresenter.prepareSuccessView(outputData);

                System.out.println("Successfully added my favourite");
            }
            else {
                final DetailPageOutputData outputData = new DetailPageOutputData(false,
                        detailPageInputData.getUsername(),
                        detailPageInputData.getCocktailName(),
                        detailPageInputData.getCocktailID(),
                        detailPageInputData.getInstruction(),
                        detailPageInputData.getPhotolink(),
                        detailPageInputData.getIngredients(),
                        detailPageInputData.getImage(),
                        detailPageInputData.getPreviousViewName());
                outputData.setMessage("This cocktail is already in your favourites");
                detailPageOutputPresenter.prepareFailView(outputData);
                System.out.println("This cocktail is already in your favourites.");
            }
        }
        catch (Exception exception) {
            // Handle any errors and pass them to the presenter

            detailPageOutputPresenter.prepareFailView(
                    new DetailPageOutputData(true,
                                    "DAO error" + exception.getMessage()));
        }
    }

    @Override
    public void returnOrigin(String privousViewname) {
        detailPageOutputPresenter.returnOrigin(privousViewname);
    }

}
