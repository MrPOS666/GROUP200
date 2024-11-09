package entity;

import java.util.List;

/**
 * The representation of a cocktail in our program.
 */
public class CommonCocktail implements Cocktail {
    private final String name;
    private final String recipe;
    private final List<String> ingredients;
    private final String photolink;

    public CommonCocktail(String name, String recipe, List<String> ingredients, String photolink) {
        this.name = name;
        this.recipe = recipe;
        this.ingredients = ingredients;
        this.photolink = photolink;
    }

    @Override
    public String getName() {
        return name;
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
