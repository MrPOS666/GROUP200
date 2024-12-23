package interface_adapter.search_by_ingredients;

import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;
import use_case.search_by_ingredients.IngredientsOutputBoundary;
import use_case.search_by_ingredients.IngredientsOutputData;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

/**
 * The Presenter for the Search Use Case.
 */
public class IngredientsPresenter implements IngredientsOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public IngredientsPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(IngredientsOutputData response) {
        // On success, switch to the searched cocktail in view.
        // update the search state
        final SearchState searchState = searchViewModel.getState();
        // Extract lists from the response
        final List<Integer> ids = response.getIdDrink();
        final List<String> names = response.getCocktailName();
        final List<String> instructions = response.getInstructions();
        final List<String> photoLinks = response.getPhotoLink();
        final List<Map<String, String>> ingredients = response.getIngredients();
        final List<BufferedImage> images = response.getImages();

        searchState.setCocktailNamesList(names);
        searchState.setIdList(ids);
        searchState.setIngredientsList(ingredients);
        searchState.setRecipeList(instructions);
        searchState.setPhotoLinkList(photoLinks);
        searchState.setImages(images);

        searchViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // On failure, set the error message in the search state
        final SearchState searchState = searchViewModel.getState();
        searchState.setSearchError(errorMessage);
        searchViewModel.firePropertyChanged();
    }
}
