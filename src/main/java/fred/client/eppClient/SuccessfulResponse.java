package fred.client.eppClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Successful responses which can be returned from FRED.
 *
 * @see <a href=https://fred.nic.cz/documentation/html/EPPReference/Appendixes/ResultCodes.html?highlight=errors>FRED documentation</a>
 */
public enum SuccessfulResponse {

    RESPONSE_1000(1000, "Command completed successfully"),
    RESPONSE_1001(1001, "Command completed successfully; action pending"),
    RESPONSE_1300(1300, "Command completed successfully; no messages"),
    RESPONSE_1301(1301, "Command completed successfully; ack to dequeue"),
    RESPONSE_1500(1500, "Command completed successfully; ending session");

    private int responseNumber;

    private String responseMessage;

    SuccessfulResponse(int responseNumber, String responseMessage) {
        this.responseNumber = responseNumber;
        this.responseMessage = responseMessage;
    }

    public int getResponseNumber() {
        return responseNumber;
    }

    public String getErrorNumberAsString() {
        return String.valueOf(responseNumber);
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public static List<Integer> getAllSuccessfulCodes() {
        List<Integer> successfulCodes = new ArrayList<Integer>();
        for (SuccessfulResponse successfulResponse : SuccessfulResponse.values()) {
            successfulCodes.add(successfulResponse.getResponseNumber());
        }
        return successfulCodes;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder(SuccessfulResponse.class.getSimpleName());
        sb.append("{responseNumber=").append(responseNumber);
        sb.append(", responseMessage='").append(responseMessage).append('\'');
        sb.append('}');
        return sb.toString();
    }
}