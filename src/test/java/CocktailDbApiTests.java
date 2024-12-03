package APITest.CockTailAPITest;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

public class CocktailDbApiTests {

    private static OkHttpClient client;
    private static final String BASE_URL = "https://www.thecocktaildb.com/api/json/v1/1";

    @BeforeAll
    public static void setUp() {
        client = new OkHttpClient();
    }

    @Test
    public void testSearchCocktailByName() {
        String cocktailName = "Margarita";
        String url = BASE_URL + "/search.php?s=" + cocktailName;

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Expected HTTP status 200");
            assertNotNull(response.body(), "Response body should not be null");

            System.out.println("Search Result for " + cocktailName + ": " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLookupCocktailById() {
        String cocktailId = "11007"; // Margarita ID
        String url = BASE_URL + "/lookup.php?i=" + cocktailId;

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Expected HTTP status 200");
            assertNotNull(response.body(), "Response body should not be null");

            System.out.println("Details for Cocktail ID " + cocktailId + ": " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFilterCocktailsByIngredient() {
        String ingredient = "Gin";
        String url = BASE_URL + "/filter.php?i=" + ingredient;

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Expected HTTP status 200");
            assertNotNull(response.body(), "Response body should not be null");

            System.out.println("Cocktails with ingredient " + ingredient + ": " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListCategories() {
        String url = BASE_URL + "/list.php?c=list";

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Expected HTTP status 200");
            assertNotNull(response.body(), "Response body should not be null");

            System.out.println("Cocktail Categories: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testListGlassTypes() {
        String url = BASE_URL + "/list.php?g=list";

        Request request = new Request.Builder().url(url).build();

        try (Response response = client.newCall(request).execute()) {
            assertEquals(200, response.code(), "Expected HTTP status 200");
            assertNotNull(response.body(), "Response body should not be null");

            System.out.println("Glass Types: " + response.body().string());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
