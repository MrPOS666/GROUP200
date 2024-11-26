package use_case.interests.build;

import entity.User;

/**
 * Input Boundary for User interests HashMap generation
 */
public interface interests_buildInputBoundary {
    /**
     * Executes the interests HashMap generation use case
     * @param user
     */
    void assignInterestForUser(User user);
}
