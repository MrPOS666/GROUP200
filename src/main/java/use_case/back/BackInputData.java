package use_case.back;

/**
 * The Input Data for the Back Use Case.
 */
public class BackInputData {

    private final String cocktailName;


    public BackInputData(String cocktailName) {
        this.cocktailName = cocktailName;
    }

    String getCocktailName() {
        return cocktailName;
    }
}
