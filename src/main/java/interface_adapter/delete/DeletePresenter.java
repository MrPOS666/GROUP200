package interface_adapter.delete;

import interface_adapter.ViewModel;
import interface_adapter.myFavourite.MyFavouriteState;
import interface_adapter.myFavourite.MyFavouriteViewModel;
import interface_adapter.search.SearchState;
import use_case.delete_favorite.DeleteOutputBoundary;
import use_case.delete_favorite.DeleteOutputData;

import java.util.List;
import java.util.Map;

/**
 * The Presenter for the Delete Use Case.
 */
public class DeletePresenter implements DeleteOutputBoundary {

    private final MyFavouriteViewModel myFavouriteViewModel;
    private final ViewModel viewManagerModel;

    public DeletePresenter(MyFavouriteViewModel myFavouriteViewModel, ViewModel viewManagerModel) {
        this.myFavouriteViewModel = myFavouriteViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the Delete Use Case.
     *
     * @param response the output data
     */
    @Override
    public void prepareSuccessView(DeleteOutputData response) {
        // On success, switch to the searched cocktail in view.
        // update the search state
        final MyFavouriteState myFavouriteState = myFavouriteViewModel.getState();
        // Extract lists from the response
        final List<Integer> ids = response.getIdDrink();
        final List<String> names = response.getCocktailName();
        final List<String> instructions = response.getInstructions();
        final List<String> photoLinks = response.getPhotoLink();
        final List<Map<String, String>> ingredients = response.getIngredients();

        myFavouriteState.setCocktailNamesList(names);
        myFavouriteState.setIdList(ids);
        myFavouriteState.setIngredientsList(ingredients);
        myFavouriteState.setInstructionList(instructions);
        myFavouriteState.setPhotoLinkList(photoLinks);

        myFavouriteViewModel.firePropertyChanged();

        this.viewManagerModel.setState(myFavouriteViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // On failure, set the error message in the search state
        final MyFavouriteState myFavouriteState = myFavouriteViewModel.getState();
        myFavouriteState.setSelectError(errorMessage);
        myFavouriteViewModel.firePropertyChanged();
    }

}
