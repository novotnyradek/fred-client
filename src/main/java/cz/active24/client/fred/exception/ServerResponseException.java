package cz.active24.client.fred.exception;

import cz.active24.client.fred.eppclient.ErrorResponse;

import java.util.List;

/**
 *  Exception is thrown when server returns error.
 */
public class ServerResponseException extends FredClientException {

    private List<ErrorResponse> errors;

    public ServerResponseException(List<ErrorResponse> errors) {
        super("Server returned error!");
        this.errors = errors;
    }

    public List<ErrorResponse> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorResponse> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Server returned error! ");
        sb.append("Errors=").append(errors);
        return sb.toString();
    }
}
