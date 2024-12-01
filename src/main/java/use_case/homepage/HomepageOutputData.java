package use_case.homepage;

/**
 * Output Data for the Homepage Use Case.
 */
public class HomepageOutputData {
    private String username;

    public HomepageOutputData(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
