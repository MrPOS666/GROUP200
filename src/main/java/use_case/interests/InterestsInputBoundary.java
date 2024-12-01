package use_case.interests;

import entity.User;

import java.io.IOException;

/**
 * Input Boundary for User interests HashMap generation
 */
public interface InterestsInputBoundary {

    void execute(InterestsInputData interestsInputData) throws IOException;
}
