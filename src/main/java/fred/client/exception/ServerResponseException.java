package fred.client.exception;

/**
 *  Exception is thrown when server returns error.
 */
public class ServerResponseException extends FredClientException {

    private String errorCode;

    public ServerResponseException(String message) {
        super(message);
    }

    public ServerResponseException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ServerResponseException(String errorCode, String message, Throwable throwable) {
        super(message, throwable);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(this.getClass().getName()+": ");
        sb.append("Server returned error! [ERROR_CODE: ").append(errorCode).append(", ERROR_MESSAGE: ").append(getMessage()).append("]");
        return sb.toString();
    }
}
