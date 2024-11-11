package use_case.back;

/**
 * Input Boundary for actions which are related to switching back to the search page.
 */
public interface BackInputBoundary {

    /**
     * Executes the back use case.
     * @param BackInputData the input data.
     */
    void execute(BackInputData BackInputData);
}
