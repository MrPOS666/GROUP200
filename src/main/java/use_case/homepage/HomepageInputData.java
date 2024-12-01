package use_case.homepage;

/**
 * The Input Data for the homepage Use case.
 */
public class HomepageInputData {

    private String username;

    public HomepageInputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
