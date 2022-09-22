package com.restapi.firstjavacrud.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.restapi.firstjavacrud.viewers.ApiBodyResponseViewer;

/**
 * When an ApiRequestException is thrown, return a ResponseEntity with the
 * exception's message and
 * timestamp, and the exception's HTTP status.
 */
@ControllerAdvice
/**
 * This class is a controller advice that handles exceptions thrown by the
 * controller
 */
public class ApiExceptionHandler {
    /**
     * This function is handling the exception thrown by the controller
     * 
     * @param exception The exception object that was thrown by the controller.
     * @return The response entity is being returned.
     */
    @ExceptionHandler(value = { ApiRequestException.class })
    // This method is handling the exception thrown by the controller.
    public ResponseEntity<Object> handleApiException(Exception exception) {
        ApiRequestException exceptionArgs = (ApiRequestException) exception;
        return new ResponseEntity<>(
                new ApiBodyResponseViewer(
                        exceptionArgs.getMessage()),
                exceptionArgs.getHttpStatus());
    }
}