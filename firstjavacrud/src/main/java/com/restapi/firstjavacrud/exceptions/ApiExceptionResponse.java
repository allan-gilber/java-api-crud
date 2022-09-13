package com.restapi.firstjavacrud.exceptions;

/**
 * This class is used to create a JSON response when an exception is thrown
 */

public class ApiExceptionResponse {
    private final String message;
    private final String timestamp;

    public ApiExceptionResponse(
            String message,
            String timestamp) {
        this.message = message;
        this.timestamp = timestamp;
    }

    /**
     * This function returns the message of the object
     * 
     * @return The message is being returned.
     */
    public String getMessage() {
        return message;
    }

    /**
     * This function returns the timestamp of the message
     * 
     * @return The timestamp.
     */
    public String getTimestamp() {
        return timestamp;
    }

}