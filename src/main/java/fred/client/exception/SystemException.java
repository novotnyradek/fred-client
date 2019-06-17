package fred.client.exception;

/**
 *  Exception is thrown when some system processing failed.
 */
public class SystemException extends FredClientException {

    public SystemException(String message) {
        super(message);
    }

    public SystemException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
