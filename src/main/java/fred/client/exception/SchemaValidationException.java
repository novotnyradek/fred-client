package fred.client.exception;

/**
 * Exception is thrown when validation against provided xsd schemas failed.
 */
public class SchemaValidationException extends FredClientException {

    private String customMessage;

    public SchemaValidationException(String message) {
        super(message);
    }

    public SchemaValidationException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public SchemaValidationException(String customMessage, String message) {
        super(message);
        this.customMessage = customMessage;
    }

    public SchemaValidationException(String customMessage, String message, Throwable throwable) {
        super(message, throwable);
        this.customMessage = customMessage;
    }
}
