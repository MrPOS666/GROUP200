package data_access;

import entity.Cocktail;
import entity.CommonCocktail;
import entity.CommonUser;
import entity.User;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import use_case.detailPage.DetailPageDataAccessException;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DBUserDataAccessObject2Test {
    private DBUserDataAccessObject2 userDao;
    private User testUser;

    @Test
    public void testSaveUser() {
        userDao = new DBUserDataAccessObject2();
        testUser = new CommonUser("testUser1", "password123", new ArrayList<>());
        try {
            userDao.saveUser(testUser);
            // If no exception is thrown, the save operation was successful
        } catch (DetailPageDataAccessException e) {
            fail("Failed to save user: " + e.getMessage());
        }
    }

    @Test
    public void testLoadUser() {
        userDao = new DBUserDataAccessObject2();
        testUser = new CommonUser("testUser", "password123", new ArrayList<>());
        try {
            User loadedUser = userDao.loadUserByName("testUser");

            // Validate the loaded user
            assertEquals(testUser.getName(), loadedUser.getName());
            assertEquals(testUser.getPassword(), loadedUser.getPassword());
            assertTrue(loadedUser.getMyFavourite().isEmpty()); // Initially empty favorite list
        } catch (DetailPageDataAccessException e) {
            fail("Failed to load user: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateMyFavourite() {
        userDao = new DBUserDataAccessObject2();
        testUser = new CommonUser("testUser", "password123", new ArrayList<>());
        try {
            // First, save the test user

            // Create a list of favorite cocktails
            List<Cocktail> favorites = new ArrayList<>();
            Map<String, String> ingredients = new HashMap<>();
            ingredients.put("ingredient1", "50ml");
            ingredients.put("ingredient2", "100ml");
            Cocktail cocktail = null;
            try {
                cocktail = new CommonCocktail(11007,
                                                "Margarita",
                                            "Mix and serve.",
                                                "https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg",
                                                        ingredients,
                                                        ImageDataAccessObject.fetchImage("https://www.thecocktaildb.com/images/media/drink/5noda61589575158.jpg"));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            favorites.add(cocktail);

            // Update the user's favorites
            userDao.updateMyFavouriteCocktail(testUser, favorites);

            // Reload the user and validate the favorites list
            User updatedUser = userDao.loadUserByName("testUser");
            assertEquals(1, updatedUser.getMyFavourite().size());
            assertEquals(cocktail.getIdDrink(), updatedUser.getMyFavourite().get(0).getIdDrink());
        } catch (DetailPageDataAccessException e) {
            fail("Failed to update favorites: " + e.getMessage());
        }
    }

    @Test
    public void testUpdateEmptyFavorites() {
        userDao = new DBUserDataAccessObject2();
        testUser = new CommonUser("testUser", "password123", new ArrayList<>());
        try {
            // First, save the test user

            // Update the user's favorites with an empty list
            userDao.updateMyFavouriteCocktail(testUser, new ArrayList<>());

            // Reload the user and validate the favorites list is empty
            User updatedUser = userDao.loadUserByName("testUser");
            assertTrue(updatedUser.getMyFavourite().isEmpty());
        } catch (DetailPageDataAccessException e) {
            fail("Failed to update favorites: " + e.getMessage());
        }
    }

    @Test
    public void testLoadNonExistentUser() {
        userDao = new DBUserDataAccessObject2();
        testUser = new CommonUser("testUser", "password123", new ArrayList<>());
        try {
            // Attempt to load a user that does not exist
            userDao.loadUserByName("nonExistentUser");
            fail("Expected an exception for non-existent user");
        } catch (DetailPageDataAccessException e) {
            // Exception is expected, validate the message
            assertTrue(e.getMessage().contains("Error loading user"));
        }
    }

    @Test
    public void testSaveDuplicateUser() {
        userDao = new DBUserDataAccessObject2();
        testUser = new CommonUser("testUser", "password123", new ArrayList<>());
        try {
            // Save the same user twice
            userDao.saveUser(testUser);
            userDao.saveUser(testUser);
            fail("Expected an exception for duplicate user");
        } catch (DetailPageDataAccessException e) {
            // Exception is expected, validate the message
            assertTrue(e.getMessage().contains("Error saving user"));
        }
    }
}
