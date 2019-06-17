package fred.client.exception;

/**
 * Exception is thrown when input validation failed.
 */
public class ValidationException extends FredClientException {

    private String errorCode;

    public ValidationException(String message) {
        super(message);
    }

    public ValidationException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ValidationException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }
}
