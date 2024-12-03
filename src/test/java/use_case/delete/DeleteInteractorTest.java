package use_case.delete;

import data_access.DBUserDataAccessObject2;
import data_access.SearchByNameOrIDAccessObject;
import entity.*;
import org.junit.Test;
import use_case.delete_favorite.*;
import use_case.detailPage.DetailPageDataAccessException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeleteInteractorTest {

    private DeleteDataAccessInterface dbUserDataAccessObject = new DBUserDataAccessObject2();
    private SearchByNameOrIDAccessObject searchDataAccessObject =
            new SearchByNameOrIDAccessObject(new CommonCocktailFactory());

    @Test
    public void successTest() {

        User testUser = new CommonUserFactory().create("Paul2", "207");
        // Save test user to the mock API
        try {
            dbUserDataAccessObject.saveUserToApi(testUser);
        }
        catch (DetailPageDataAccessException e) {
            fail("Failed to set up test user: " + e.getMessage());
        }

        int cocktailId = 11007;
        Cocktail cocktail;
        try {
            cocktail = searchDataAccessObject.getById(cocktailId);
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch cocktail by ID: " + e.getMessage());
        }

        // Add the cocktail to the user's favorite list
        List<Cocktail> cocktails = new ArrayList<>();
        cocktails.add(cocktail);
        try {
            dbUserDataAccessObject.updateMyFavouriteCocktail(testUser, cocktails);
        } catch (DetailPageDataAccessException e) {
            throw new RuntimeException("Failed to save user's favorite cocktails: " + e.getMessage());
        }

        List<Integer> deletedCocktailId = new ArrayList<>();
        deletedCocktailId.add(cocktailId);
        DeleteInputData inputData = new DeleteInputData(deletedCocktailId, testUser.getName());

        DeleteOutputBoundary deletePresenter = new DeleteOutputBoundary() {
            @Override
            public void prepareSuccessView(DeleteOutputData deleteOutputData) {
                // Ensure the returned data does not include the deleted cocktail
                assertFalse(deleteOutputData.getIdDrink().contains(cocktailId));
                assertEquals(0, deleteOutputData.getIdDrink().size());
            }

            @Override
            public void prepareFailView(String errorMessage) {
                fail("Use case failure is unexpected: " + errorMessage);
            }

            @Override
            public void switchToHomepageView() {
            }
        };

        // Execute the delete use case
        DeleteInputBoundary interactor = new DeleteInteractor(dbUserDataAccessObject, deletePresenter);
        try {
            interactor.execute(inputData);
        } catch (DetailPageDataAccessException e) {
            throw new RuntimeException("Unexpected failure during delete execution: " + e.getMessage());
        }

        // Verify that the user's favorite list no longer contains the deleted cocktail
        List<Cocktail> updatedFavorites = testUser.getMyFavourite();
        assertFalse(updatedFavorites.contains(cocktail));
    }

}
