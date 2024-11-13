package data_access;

import entity.Cocktail;
import entity.CommonCocktailFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class SearchByNameOrIDAccessObjectTest {

    private SearchByNameOrIDAccessObject searchDataAccessObject;

    @BeforeEach
    void setUp() {
        // Initialize SearchByNameOrIDAccessObject with CommonCocktailFactory
        searchDataAccessObject = new SearchByNameOrIDAccessObject(new CommonCocktailFactory());
    }

    @Test
    void testPrintCocktailsByName() {
        // Assuming "Margarita" will return multiple results
        String cocktailName = "Margarita";
        List<Cocktail> cocktails = searchDataAccessObject.getByName(cocktailName);

        System.out.println("Cocktails found by name " + cocktailName + ":");
        for (Cocktail cocktail : cocktails) {
            printCocktailDetails(cocktail);
        }
    }

    @Test
    void testPrintCocktailById() {
        // Assuming ID 11007 corresponds to a valid cocktail (e.g., Margarita)
        int cocktailId = 11007;
        Cocktail cocktail = searchDataAccessObject.getById(cocktailId);

        System.out.println("Cocktail found by ID" + cocktailId + ":");
        if (cocktail != null) {
            printCocktailDetails(cocktail);
        } else {
            System.out.println("No cocktail found with ID" + cocktailId);
        }
    }

    // Helper method to print details of a Cocktail object
    private void printCocktailDetails(Cocktail cocktail) {
        System.out.println("Cocktail Name: " + cocktail.getCocktailName());
        System.out.println("Recipe: " + cocktail.getInstructions());
        System.out.println("Photo Link: " + cocktail.getPhotoLink());
        System.out.println("Ingredients:");
        cocktail.getIngredients().forEach((ingredient, measure) ->
                System.out.println("  - " + ingredient + ": " + measure)
        );
        System.out.println("----------------------------------------------------");
    }
}
