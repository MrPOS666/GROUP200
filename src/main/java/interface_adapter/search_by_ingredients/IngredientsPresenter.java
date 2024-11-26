package interface_adapter.search_by_ingredients;

import interface_adapter.ViewManagerModel;
import use_case.search_by_ingredients.IngredientsOutputBoundary;
import use_case.search_by_ingredients.IngredientsOutputData;

import java.util.List;
import java.util.Map;

/**
 * The Presenter for the Search Use Case.
 */
public class IngredientsPresenter implements IngredientsOutputBoundary {

    private final IngredientsViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public IngredientsPresenter(IngredientsViewModel ingredientsViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = ingredientsViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(IngredientsOutputData response) {
        // On success, switch to the searched cocktail in view.
        // update the search state
        final IngredientsState ingredientsState = searchViewModel.getState();
        // Extract lists from the response
        final List<Integer> ids = response.getIdDrink();
        final List<String> names = response.getCocktailName();
        final List<String> instructions = response.getInstructions();
        final List<String> photoLinks = response.getPhotoLink();
        final List<Map<String, String>> ingredients = response.getIngredients();

        ingredientsState.setCocktailNamesList(names);
        ingredientsState.setIdList(ids);
        ingredientsState.setIngredientsList(ingredients);
        ingredientsState.setRecipeList(instructions);
        ingredientsState.setPhotoLinkList(photoLinks);

        searchViewModel.firePropertyChanged();

        this.viewManagerModel.setState(searchViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // On failure, set the error message in the search state
        final IngredientsState ingredientsState = searchViewModel.getState();
        ingredientsState.setIngredientsError(errorMessage);
        searchViewModel.firePropertyChanged();
    }
}
