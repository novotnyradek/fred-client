package fred.client.exception;

/**
 * Exception is parent of all exceptions in this project.
 */
public class FredClientException extends Exception {

    public FredClientException(String message){
        super(message);
    }

    public FredClientException(String message, Throwable throwable){
        super(message, throwable);
    }

    // todo get error code, get message, possibly get reason

}
