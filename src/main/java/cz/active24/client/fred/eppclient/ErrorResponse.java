package cz.active24.client.fred.eppclient;

import java.util.ArrayList;
import java.util.List;

/**
 * Error responses which can be returned from FRED.
 *
 * <ul>
 * <li>{@link ErrorResponse#code} - result code (4-digit number)</li>
 * <li>{@link ErrorResponse#message} - human-readable description of the result</li>
 * </ul>
 *
 * @see <a href=https://fred.nic.cz/documentation/html/EPPReference/Appendixes/ResultCodes.html>FRED documentation</a>
 */
public enum ErrorResponse {

    ERROR_2000(2000, "Unknown command"),
    ERROR_2001(2001, "Command syntax error"),
    ERROR_2002(2002, "Command use error"),
    ERROR_2003(2003, "Required parameter missing"),
    ERROR_2004(2004, "Parameter value range error"),
    ERROR_2005(2005, "Parameter value syntax error"),
    ERROR_2100(2100, "Unimplemented protocol version"),
    ERROR_2101(2101, "Unimplemented command"),
    ERROR_2102(2102, "Unimplemented option"),
    ERROR_2103(2103, "Unimplemented extension"),
    ERROR_2104(2104, "Billing failure"),
    ERROR_2105(2105, "Object is not eligible for renewal"),
    ERROR_2106(2106, "Object is not eligible for transfer"),
    ERROR_2200(2200, "Authentication error"),
    ERROR_2201(2201, "Authorization error"),
    ERROR_2202(2202, "Invalid authorization information"),
    ERROR_2300(2300, "Object pending transfer"),
    ERROR_2301(2301, "Object not pending transfer"),
    ERROR_2302(2302, "Object exists"),
    ERROR_2303(2303, "Object does not exist"),
    ERROR_2304(2304, "Object status prohibits operation"),
    ERROR_2305(2305, "Object association prohibits operation"),
    ERROR_2306(2306, "Parameter value policy error"),
    ERROR_2307(2307, "Unimplemented object service"),
    ERROR_2308(2308, "Data management policy violation"),
    ERROR_2400(2400, "Command failed"),
    ERROR_2500(2500, "Command failed; server closing connection"),
    ERROR_2501(2501, "Authentication error; server closing connection"),
    ERROR_2502(2502, "Session limit exceeded; server closing connection");

    private int code;

    private String message;

    ErrorResponse(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public static List<Integer> getAllErrorCodes() {
        List<Integer> errorCodes = new ArrayList<Integer>();
        for (ErrorResponse error : ErrorResponse.values()) {
            errorCodes.add(error.getCode());
        }
        return errorCodes;
    }

    public static ErrorResponse fromValue(int errorCode) {
        for (ErrorResponse value : ErrorResponse.values()) {
            if (value.getCode() == errorCode) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.valueOf(errorCode));
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ERROR_CODE: ");
        sb.append(code);
        sb.append(", ERROR_MESSAGE: ").append(message);
        return sb.toString();
    }
}