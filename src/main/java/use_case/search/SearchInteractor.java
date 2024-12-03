package use_case.search;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import entity.Cocktail;

/**
 * The Search Interactor.
 */
public class SearchInteractor implements SearchInputBoundary {
    private final SearchDataAccessInterface searchDataAccessObject;
    private final SearchOutputBoundary searchPresenter;

    public SearchInteractor(SearchDataAccessInterface searchDataAccessInterface,
                            SearchOutputBoundary searchOutputBoundary) {
        this.searchDataAccessObject = searchDataAccessInterface;
        this.searchPresenter = searchOutputBoundary;
    }

    /**
     * Executes the search use case.
     *
     * @param searchInputData the input data
     */
    @Override
    public void execute(SearchInputData searchInputData) throws IOException {

        if (searchInputData.isSearchByName()) {
            if (searchDataAccessObject.existsByName(searchInputData.getInput())) {
                final List<Cocktail> cocktails = searchDataAccessObject.getByName(searchInputData.getInput());

                final List<Integer> ids = new ArrayList<>();
                final List<String> names = new ArrayList<>();
                final List<String> instructions = new ArrayList<>();
                final List<String> photoLinks = new ArrayList<>();
                final List<Map<String, String>> ingredients = new ArrayList<>();
                final List<BufferedImage> images = new ArrayList<>();

                for (Cocktail cocktail : cocktails) {
                    ids.add(cocktail.getIdDrink());
                    names.add(cocktail.getCocktailName());
                    instructions.add(cocktail.getInstructions());
                    photoLinks.add(cocktail.getPhotoLink());
                    ingredients.add(cocktail.getIngredients());
                    images.add(cocktail.getImage());
                }
                searchPresenter.prepareSuccessView(new SearchOutputData(false,
                        ids, names, instructions, photoLinks, ingredients, images));
            }
            else {
                searchPresenter.prepareFailView("Cocktail with name '" + searchInputData.getInput() + "' not found.");
            }
        }
        else if (searchInputData.isSearchById()) {
            final int cocktailId = Integer.parseInt(searchInputData.getInput());
            if (searchDataAccessObject.existsById(cocktailId)) {
                final Cocktail cocktail = searchDataAccessObject.getById(cocktailId);
                final List<Integer> ids = new ArrayList<>();
                final List<String> names = new ArrayList<>();
                final List<String> instructions = new ArrayList<>();
                final List<String> photoLinks = new ArrayList<>();
                final List<Map<String, String>> ingredients = new ArrayList<>();
                final List<BufferedImage> images = new ArrayList<>();
                ids.add(cocktail.getIdDrink());
                names.add(cocktail.getCocktailName());
                instructions.add(cocktail.getInstructions());
                photoLinks.add(cocktail.getPhotoLink());
                ingredients.add(cocktail.getIngredients());
                images.add(cocktail.getImage());
                searchPresenter.prepareSuccessView(new SearchOutputData(false,
                        ids, names, instructions, photoLinks, ingredients, images));
            }
            else {
                searchPresenter.prepareFailView("Cocktail with ID '" + searchInputData.getInput() + "' not found.");
            }
        }
        else {
            searchPresenter.prepareFailView("Invalid search input. Please enter a valid name or ID.");
        }
    }
}
