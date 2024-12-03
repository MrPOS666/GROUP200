package use_case.search;

import data_access.SearchByNameOrIDAccessObject;
import entity.CommonCocktailFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import interface_adapter.search_by_ingredients.IngredientsPresenter;
import org.junit.jupiter.api.Test;
import use_case.search_by_ingredients.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class IngredientsInteractorTest {
    private final SearchByNameOrIDAccessObject searchDataAccessObject =
            new SearchByNameOrIDAccessObject(new CommonCocktailFactory());

    @Test
    void ingredientsSuccessTest() {
        List<String> cocktailIngredients = new ArrayList<>();
        cocktailIngredients.add("Salt");
        IngredientsInputData inputData = new IngredientsInputData(cocktailIngredients);
        // Mock the output boundary to test behavior
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchViewModel searchViewModel = new SearchViewModel("search");
        IngredientsOutputBoundary ingredientsOutputBoundary = new IngredientsPresenter(searchViewModel, viewManagerModel) {
            @Override
            public void prepareSuccessView(IngredientsOutputData response) {
                // Verify that the output contains expected cocktail names
                int num_results = response.getCocktailName().size();
                assertTrue(
                        num_results == 4, "Should receive 4 results"
                );
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };


        // Instantiate the interactor with dependencies
        IngredientsInputBoundary ingredientsInteractor = new IngredientsInteractor(searchDataAccessObject, ingredientsOutputBoundary);

        // Execute the use case
        ingredientsInteractor.execute(inputData);
    }

}
