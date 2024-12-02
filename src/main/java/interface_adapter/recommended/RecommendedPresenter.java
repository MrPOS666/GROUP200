package interface_adapter.recommended;

import interface_adapter.ViewModel;
//import interface_adapter.recommended.RecommendedState;
//import interface_adapter.recommended.RecommendedViewModel;
import interface_adapter.homepage.HomepageViewModel;
import use_case.recommended.RecommendedOutputBoundary;
import use_case.recommended.RecommendedOutputData;

import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Map;

public class RecommendedPresenter implements RecommendedOutputBoundary {

    private final RecommendedViewModel recommendedViewModel;
    private final ViewModel viewManagerModel;
    private final HomepageViewModel homepageViewModel;

    public RecommendedPresenter(RecommendedViewModel recommendedViewModel,
                                ViewModel viewManagerModel,
                                HomepageViewModel homepageViewModel) {
        this.recommendedViewModel = recommendedViewModel;
        this.viewManagerModel = viewManagerModel;
        this.homepageViewModel = homepageViewModel;
    }

    @Override
    public void prepareSuccessView(RecommendedOutputData response) {
        final RecommendedState recommendedState = recommendedViewModel.getState();
        final List<Integer> ids = response.getIdDrink();
        final List<String> names = response.getCocktailName();
        final List<String> instructions = response.getInstructions();
        final List<String> photoLinks = response.getPhotoLink();
        final List<Map<String, String>> ingredients = response.getIngredients();
        final String username = response.getUsername();

        recommendedState.setCocktailNamesList(names);
        recommendedState.setIdList(ids);
        recommendedState.setIngredientsList(ingredients);
        recommendedState.setInstructionList(instructions);
        recommendedState.setPhotoLinkList(photoLinks);
        recommendedState.setUsername(username);

        recommendedViewModel.setState(recommendedState);

        recommendedViewModel.firePropertyChanged();

        this.viewManagerModel.setState(recommendedViewModel.getViewName());
        this.viewManagerModel.firePropertyChanged();
    }

    @Override
    public void prepareFailView(String errorMessage) {
        // On failure, set the error message in the search state
        final RecommendedState recommendedState = recommendedViewModel.getState();
        recommendedState.setRecommendedError(errorMessage);
        recommendedViewModel.firePropertyChanged();
    }

    /**
     * Switch to the Homepage View.
     */
    @Override
    public void switchToHomepageView() {
        viewManagerModel.setState(homepageViewModel.getViewName());
        viewManagerModel.firePropertyChanged();
    }
}
