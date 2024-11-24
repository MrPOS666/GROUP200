package use_case.delete_favorite;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Cocktail;
import entity.User;

/**
 * The Delete Interactor.
 */
public class DeleteInteractor implements DeleteInputBoundary {
    private final DeleteDataAccessInterface deleteDataAccessObject;
    private final DeleteOutputBoundary deletePresenter;

    public DeleteInteractor(DeleteDataAccessInterface deleteDataAccessObject, DeleteOutputBoundary deletePresenter) {
        this.deleteDataAccessObject = deleteDataAccessObject;
        this.deletePresenter = deletePresenter;
    }

    /**
     * Executes the delete use case.
     *
     * @param deleteInputData the input data
     */
    @Override
    public void execute(DeleteInputData deleteInputData) {
        final String username = deleteInputData.getUserName();
        final List<Integer> cocktailIdToDelete = deleteInputData.getDeleteCocktailId();

        final User user = deleteDataAccessObject.getUserByUsername(username);
        if (user == null) {
            deletePresenter.prepareFailView("User not found: " + username);
        }

        final List<Cocktail> favourites = user.getMyFavourite();
        final List<Cocktail> updateFavourites = new ArrayList<>(favourites);
        boolean deleted = false;

        for (Cocktail cocktail: favourites) {
            if (cocktailIdToDelete.contains(cocktail.getIdDrink())) {
                updateFavourites.remove(cocktail);
                deleted = true;
            }
        }

        if (deleted) {
            user.getMyFavourite().clear();
            user.getMyFavourite().addAll(updateFavourites);
            deleteDataAccessObject.updateFavorite(user);

            final DeleteOutputData outputData = createOutputData(updateFavourites);
            deletePresenter.prepareSuccessView(outputData);
        }
        else {
            deletePresenter.prepareFailView("No matching cocktails found for deletion.");
        }
    }

    private DeleteOutputData createOutputData(List<Cocktail> updatedFavourites) {
        final List<Integer> ids = new ArrayList<>();
        final List<String> names = new ArrayList<>();
        final List<String> instructions = new ArrayList<>();
        final List<String> photos = new ArrayList<>();
        final List<Map<String, String>> ingredients = new ArrayList<>();

        for (Cocktail cocktail : updatedFavourites) {
            ids.add(cocktail.getIdDrink());
            names.add(cocktail.getCocktailName());
            instructions.add(cocktail.getInstructions());
            photos.add(cocktail.getPhotoLink());
            ingredients.add(cocktail.getIngredients());
        }
        return new DeleteOutputData(false, ids, names, instructions, photos, ingredients);
    }
}
