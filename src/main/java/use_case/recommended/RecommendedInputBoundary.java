package use_case.recommended;

import java.io.IOException;

public interface RecommendedInputBoundary {
    void execute(RecommendedInputData recommendedInputData) throws IOException;
}
