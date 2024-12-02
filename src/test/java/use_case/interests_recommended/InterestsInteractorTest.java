package use_case.interests;

import entity.Cocktail;
import entity.CommonUserFactory;
import entity.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InterestsInteractorTest {

    @Test
    public void testInterestsHashMapPopulation() {
        // Step 1: Create a test user
        User testUser = new CommonUserFactory().create("Paul2", "password");

        // Step 2: Create mock cocktails with ingredients
        Cocktail cocktail1 = new Cocktail("Mojito", Map.of("Mint", 2, "Sugar", 1));
        Cocktail cocktail2 = new Cocktail("Martini", Map.of("Gin", 1, "Vermouth", 1, "Sugar", 1));

        // Step 3: Add cocktails to user's favorite list
        List<Cocktail> favoriteCocktails = new ArrayList<>();
        favoriteCocktails.add(cocktail1);
        favoriteCocktails.add(cocktail2);
        testUser.setMyFavourite(favoriteCocktails);

        // Step 4: Mock the data access interface
        InterestsDataAccessInterface mockDataAccess = new InterestsDataAccessInterface() {
            @Override
            public User getUser(String username) {
                return testUser;
            }
        };

        // Step 5: Create the interactor and execute the use case
        InterestsInteractor interactor = new InterestsInteractor(mockDataAccess);
        InterestsInputData inputData = new InterestsInputData(testUser.getName());
        interactor.execute(inputData);

        // Step 6: Prepare expected results
        HashMap<String, Integer> expectedHashMap = new HashMap<>();
        expectedHashMap.put("Mint", 2);
        expectedHashMap.put("Sugar", 2); // Appears in both cocktails
        expectedHashMap.put("Gin", 1);
        expectedHashMap.put("Vermouth", 1);

        // Step 7: Verify the results
        assertEquals(expectedHashMap, interactor.getInterestsHashMap());
    }
}
