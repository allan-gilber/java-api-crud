package com.restapi.javacrudapicrud.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * This class is a custom exception that extends RuntimeException and is used to
 * return a custom error
 * message to the client.
 */

public class ApiRequestException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    private String zoneId = "America/Sao_Paulo";

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String timestamp = ZonedDateTime.now(ZoneId.of(zoneId))
            .format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z"));

    /**
     * HttpStatus is optional. Default is "HttpStatus.INTERNAL_SERVER_ERROR".
     * 
     * @param message
     * @param httpStatus Optional param
     * 
     */
    public ApiRequestException(
            String message,
            HttpStatus httpStatus) {
        this(message);
        if (httpStatus != null)
            setHttpStatus(httpStatus);
    }

    /**
     * Creates a instance of exception with only message param
     * 
     * @param message
     */
    public ApiRequestException(String message) {
        super(message);
    }

    /**
     * @return Return the actual timestamp formated in "MM/dd/yyyy - HH:mm:ss z"
     */
    public String getTimestamp() {
        return timestamp;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}