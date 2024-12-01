package use_case.interests;

import entity.Cocktail;
import entity.User;

import java.util.List;

public class InterestsInputData {

    private final User user;

    public InterestsInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
