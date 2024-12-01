package use_case.recommended;

import entity.Cocktail;

import java.util.List;

public class RecommendedOutputData {
    private final List<Cocktail> cocktails;

    public RecommendedOutputData(List<Cocktail> cocktails) {
        this.cocktails = cocktails;
    }

    public List<Cocktail> getCocktails() {
        return cocktails;
    }
}
