package interface_adapter.search;

import interface_adapter.ViewManagerModel;
// import interface_adapter.back.ResultState;
// import interface_adapter.back.ResultViewModel;
import use_case.search.SearchOutputBoundary;
import use_case.search.SearchOutputData;
import view.SearchView;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchState;
import interface_adapter.search.SearchViewModel;

/**
 * The Presenter for the Search Use Case.
 */
public class SearchPresenter implements SearchOutputBoundary {

    private final SearchViewModel searchViewModel;
    private final ViewManagerModel viewManagerModel;

    public SearchPresenter(SearchViewModel searchViewModel, ViewManagerModel viewManagerModel) {
        this.searchViewModel = searchViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    @Override
    public void prepareSuccessView(SearchOutputData searchOutputData) {
        // On success, switch to the searched cocktail in view.
        // update the search state
        final SearchState searchState = searchViewModel.getState();

        searchState.setId(searchOutputData.getIdDrink());
        searchState.setCocktailName(searchOutputData.getCocktailName());
        searchState.setRecipe(searchOutputData.getRecipe());
        searchState.setIngredients(searchOutputData.getIngredients());
        searchState.setPhotoLink(searchOutputData.getPhotoLink());

        this.searchViewModel.setState(searchState);
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
