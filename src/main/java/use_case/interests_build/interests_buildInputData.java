package use_case.interests_build;

import entity.User;

public class interests_buildInputData {

    private final User user;

    public interests_buildInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
