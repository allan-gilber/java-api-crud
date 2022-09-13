package com.restapi.firstjavacrud.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * It's a custom exception class that extends RuntimeException and has a
 * HttpStatus property
 */
public class ApiRequestException extends RuntimeException {
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private String timestamp;

    /**
     * @param message
     * @param httpStatus // Optional param
     */
    public ApiRequestException(
            String message,
            HttpStatus httpStatus) {
        this(message);
        if (httpStatus != null)
            this.httpStatus = httpStatus;
        this.timestamp = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"))
                .format(DateTimeFormatter.ofPattern("MM/dd/yyyy - HH:mm:ss z"));
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

    /**
     * This function returns the HTTP status code of the response
     * 
     * @return The httpStatus variable is being returned.
     */
    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}