package use_case.search;

import data_access.SearchByNameOrIDAccessObject;
import entity.Cocktail;
import entity.CommonCocktailFactory;
import interface_adapter.ViewManagerModel;
import interface_adapter.search.SearchPresenter;
import interface_adapter.search.SearchViewModel;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SearchInteractorTest {
    private final SearchByNameOrIDAccessObject searchDataAccessObject =
            new SearchByNameOrIDAccessObject(new CommonCocktailFactory());

    @Test
    void nameSuccessTest() {
        SearchInputData inputData = new SearchInputData("Margarita");
        // Mock the output boundary to test behavior
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchViewModel searchViewModel = new SearchViewModel("search");
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel) {
            @Override
            public void prepareSuccessView(SearchOutputData response) {
                // Verify that the output contains expected cocktail names
                List<String> cocktailNames = response.getCocktailName();
                assertTrue(
                        cocktailNames.stream().allMatch(name -> name.toLowerCase().contains("margarita")),
                        "All cocktail names should contain 'margarita'."
                );
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };


        // Instantiate the interactor with dependencies
        SearchInputBoundary searchInteractor = new SearchInteractor(searchDataAccessObject, searchOutputBoundary);

        // Execute the use case
        searchInteractor.execute(inputData);
    }

    @Test
    void idSuccessTest() {
        SearchInputData inputData = new SearchInputData("11007");
        // Mock the output boundary to test behavior
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        SearchViewModel searchViewModel = new SearchViewModel("search");
        SearchOutputBoundary searchOutputBoundary = new SearchPresenter(searchViewModel, viewManagerModel) {
            @Override
            public void prepareSuccessView(SearchOutputData response) {
                // Verify that the output contains expected cocktail names
                List<String> cocktailNames = response.getCocktailName();
                assertTrue(
                        cocktailNames.get(0).equals("Margarita"),
                        "."
                );
            }

            @Override
            public void prepareFailView(String error) {
                fail("Use case failure is unexpected: " + error);
            }
        };


        // Instantiate the interactor with dependencies
        SearchInputBoundary searchInteractor = new SearchInteractor(searchDataAccessObject, searchOutputBoundary);

        // Execute the use case
        searchInteractor.execute(inputData);
    }
}
