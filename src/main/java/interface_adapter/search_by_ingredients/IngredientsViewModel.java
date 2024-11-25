package interface_adapter.search_by_ingredients;

import interface_adapter.ViewModel;
import interface_adapter.search_by_ingredients.IngredientsState;

/**
 * The View Model for the Search View.
 */
public class IngredientsViewModel extends ViewModel<IngredientsState> {

    public IngredientsViewModel(String viewName) {
        super("ingredients");
        setState(new IngredientsState());
    }

}
