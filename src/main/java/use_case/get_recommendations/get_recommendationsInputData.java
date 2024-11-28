package use_case.get_recommendations;

import entity.User;

public class get_recommendationsInputData {
    private User user;

    public get_recommendationsInputData(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
