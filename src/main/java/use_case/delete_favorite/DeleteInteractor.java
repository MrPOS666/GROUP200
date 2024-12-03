package use_case.delete_favorite;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Cocktail;
import entity.User;
import use_case.delete_favorite.MyfavouritePageDataAccessException;

/**
 * The Delete Interactor.
 */
public class DeleteInteractor implements DeleteInputBoundary {
    private final DeleteDataAccessInterface dbUserDataAccessObject2;
    private final DeleteOutputBoundary deletePresenter;

    public DeleteInteractor(DeleteDataAccessInterface deleteDataAccessObject, DeleteOutputBoundary deletePresenter) {
        this.dbUserDataAccessObject2 = deleteDataAccessObject;
        this.deletePresenter = deletePresenter;
    }

    /**
     * Executes the delete use case.
     *
     * @param deleteInputData the input data
     */
    @Override
    public void execute(DeleteInputData deleteInputData) throws MyfavouritePageDataAccessException {
        final String username = deleteInputData.getUserName();
        final List<Integer> cocktailIdToDelete = deleteInputData.getDeleteCocktailId();
        final User user = dbUserDataAccessObject2.loadUserByName(username);

        final List<Integer> ids = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        final List<String> instructions = new ArrayList<>();
        final List<String> photoLinks = new ArrayList<>();
        final List<Map<String, String>> ingredients = new ArrayList<>();

        // Fetch the list of favorite cocktails directly using the username
        final List<Cocktail> favourites = user.getMyFavourite();
        if (favourites == null) {
            deletePresenter.prepareFailView("No favorites found for user: " + username);
        }

        final List<Cocktail> updateFavourites = new ArrayList<>(favourites);

        for (Cocktail cocktail: favourites) {
            if (cocktailIdToDelete.contains(cocktail.getIdDrink())) {
                updateFavourites.remove(cocktail);
            }
        }
        user.getMyFavourite().clear();
        user.getMyFavourite().addAll(updateFavourites);

        final DeleteOutputData outputData = createOutputData(updateFavourites, username);
        dbUserDataAccessObject2.updateMyFavouriteCocktail(user, updateFavourites);

        deletePresenter.prepareSuccessView(outputData);

    }

    /**
     * Execute the switch to Homepage View use case.
     */
    @Override
    public void switchToHomepageView() {
        deletePresenter.switchToHomepageView();
    }

    private DeleteOutputData createOutputData(List<Cocktail> updatedFavourites, String username) {
        final List<Integer> ids = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        final List<String> instructions = new ArrayList<>();
        final List<String> photos = new ArrayList<>();
        final List<Map<String, String>> ingredients = new ArrayList<>();
        final List<BufferedImage> images = new ArrayList<>();

        for (Cocktail cocktail : updatedFavourites) {
            ids.add(cocktail.getIdDrink());
            names.add(cocktail.getCocktailName());
            instructions.add(cocktail.getInstructions());
            photos.add(cocktail.getPhotoLink());
            ingredients.add(cocktail.getIngredients());
            images.add(cocktail.getImage());
        }
        return new DeleteOutputData(false, ids, names, instructions, photos, ingredients, images, username);
    }
}
