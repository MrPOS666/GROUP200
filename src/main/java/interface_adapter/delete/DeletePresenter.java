package interface_adapter.delete;

import interface_adapter.ViewModel;
import interface_adapter.search.SearchState;
import interface_adapter.select.SelectState;
import interface_adapter.select.SelectViewModel;
import use_case.delete_favorite.DeleteOutputBoundary;
import use_case.delete_favorite.DeleteOutputData;
import use_case.search.SearchOutputData;

import javax.swing.text.View;

/**
 * The Presenter for the Delete Use Case.
 */
public class DeletePresenter implements DeleteOutputBoundary {

    private final SelectViewModel selectViewModel;
    private final ViewModel viewManagerModel;

    public DeletePresenter(SelectViewModel selectViewModel, ViewModel viewManagerModel) {
        this.selectViewModel = selectViewModel;
        this.viewManagerModel = viewManagerModel;
    }

    /**
     * Prepares the success view for the Delete Use Case.
     *
     * @param deleteOutputData the output data
     */
    @Override
    public void prepareSuccessView(DeleteOutputData deleteOutputData) {

    }

    @Override
    public void prepareFailView(String errorMessage) {
        // On failure, set the error message in the search state
        final SelectState selectState = selectViewModel.getState();
        selectState.setSelectError(errorMessage);
        selectViewModel.firePropertyChanged();
    }

}
