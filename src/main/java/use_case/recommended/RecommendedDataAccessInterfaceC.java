package use_case.recommended;

import entity.Cocktail;

import java.util.List;

public interface RecommendedDataAccessInterfaceC {
    List<Cocktail> getByIngredients(List<String> ingredients);
}
