package use_case.detailPage;

/**
 * Exception thrown when there is an error with accessing data.
 */
public class DetailPageDataAccessException extends Exception {

    public DetailPageDataAccessException(String message) {
        super(message);
    }
}
