package use_case.delete_favorite;

/**
 * Exception thrown when there is an error with accessing data.
 */
public class MyfavouritePageDataAccessException extends RuntimeException {
    public MyfavouritePageDataAccessException(String message) {
        super(message);
    }
}
