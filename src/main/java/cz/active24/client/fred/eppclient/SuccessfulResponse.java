package cz.active24.client.fred.eppclient;

import java.util.ArrayList;
import java.util.List;

/**
 * Successful responses which can be returned from FRED.
 *
 * <ul>
 * <li>{@link SuccessfulResponse#code} - result code (4-digit number)</li>
 * <li>{@link SuccessfulResponse#message} - human-readable description of the result</li>
 * </ul>
 *
 * @see <a href=https://fred.nic.cz/documentation/html/EPPReference/Appendixes/ResultCodes.html>FRED documentation</a>
 */
public enum SuccessfulResponse {

    RESPONSE_1000(1000, "Command completed successfully"),
    RESPONSE_1001(1001, "Command completed successfully; action pending"),
    RESPONSE_1300(1300, "Command completed successfully; no messages"),
    RESPONSE_1301(1301, "Command completed successfully; ack to dequeue"),
    RESPONSE_1500(1500, "Command completed successfully; ending session");

    private int code;

    private String message;

    SuccessfulResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static List<Integer> getAllSuccessfulCodes() {
        List<Integer> successfulCodes = new ArrayList<Integer>();
        for (SuccessfulResponse successfulResponse : SuccessfulResponse.values()) {
            successfulCodes.add(successfulResponse.getCode());
        }
        return successfulCodes;
    }

    public static SuccessfulResponse fromValue(int code) {
        for (SuccessfulResponse value : SuccessfulResponse.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.valueOf(code));
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(SuccessfulResponse.class.getSimpleName());
        sb.append("{code=").append(code);
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}