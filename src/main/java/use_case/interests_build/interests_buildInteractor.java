package use_case.interests_build;

import entity.Cocktail;
import entity.User;

import java.util.HashMap;
import java.util.Map;

public class interests_buildInteractor {

    private final interests_buildUserDataAccessInterface dataAccess;
    private final interests_buildOutputBoundary outputBoundary;

    public interests_buildInteractor(interests_buildUserDataAccessInterface dataAccess,
                                     interests_buildOutputBoundary outputBoundary) {
        this.dataAccess = dataAccess;
        this.outputBoundary = outputBoundary;
    }

    public void interests_buildInputData(User user) {
        // grab the favourites
        var favourites = dataAccess.getUserFavourites(user);

        HashMap<String, Integer> newInterests = new HashMap<>();

        // loop favourites and mend to hashmap
        for (Cocktail cocktail : favourites) {
            Map<String, String> tags = cocktail.getCocktailTags();

            // loop through all the tags
            for (String tag : tags.keySet()) {
                // + 1 if exists
                if (newInterests.containsKey(tag)) {
                    Integer value = newInterests.get(tag) + 1;
                    newInterests.put(tag, value);
                } else { // make it exist otherwise
                    newInterests.put(tag,1);
                }
            }
        }

        dataAccess.setUserInterests(user, newInterests);

        interests_buildOutputData outputData = new interests_buildOutputData(newInterests);
        outputBoundary.insertInterestsBuildResult(outputData);
    }
}
