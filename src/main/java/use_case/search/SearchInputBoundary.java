package use_case.search;

import java.io.IOException;

/**
 * Input Boundary for actions which are related to searching.
 */
public interface SearchInputBoundary {

    /**
     * Executes the search use case.
     * @param searchInputData the input data
     */
    void execute(SearchInputData searchInputData) throws IOException;
}
