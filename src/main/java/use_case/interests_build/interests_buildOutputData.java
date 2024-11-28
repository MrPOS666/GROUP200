package use_case.interests_build;

import java.util.HashMap;

public class interests_buildOutputData {

    private final HashMap<String, Integer> newInterests;

    public interests_buildOutputData(HashMap<String, Integer> newInterests) {
        this.newInterests = newInterests;
    }

    public HashMap<String, Integer> getNewInterests() {
        return newInterests;
    }
}
