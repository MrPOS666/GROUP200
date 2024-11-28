package use_case.get_recommendations;

import entity.Cocktail;

import java.util.List;

public class get_recommendationsOutputData {
    private final List<Cocktail> cocktails;

    public get_recommendationsOutputData(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }
}
