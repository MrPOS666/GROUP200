package entity;

import java.util.List;

/**
 * The representation of a cocktail in our program.
 */
public class CommonCocktail implements Cocktail {
    private final String cocktailName;
    private final String recipe;
    private final List<String> ingredients;
    private final String photolink;

    public CommonCocktail(String cocktailName, String recipe, List<String> ingredients, String photolink) {
        this.cocktailName = cocktailName;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.photolink = photolink;
    }

    @Override
    public String getCocktailName() {
        return cocktailName;
    }

    @Override
    public String getRecipe() {
        return recipe;
    }

    @Override
    public List<String> getIngredients() {
        return ingredients;
    }

    @Override
    public String getPhotoLink() {
        return photolink;
    }
}
