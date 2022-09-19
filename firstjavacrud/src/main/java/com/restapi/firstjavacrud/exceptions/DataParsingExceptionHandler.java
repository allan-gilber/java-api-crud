package com.restapi.firstjavacrud.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class DataParsingExceptionHandler {
    String message;

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        setMessage(String.format(
                "The parameter '%s' of value '%s' could not be converted to type '%s'. Please, send a paramter of type '%s'",
                exception.getName(), exception.getValue(), exception.getRequiredType().getSimpleName(),
                exception.getRequiredType().getSimpleName()));
        ApiRequestException exceptionArgs = new ApiRequestException(
                message,
                HttpStatus.BAD_REQUEST);
        System.out.println("DataParsingExceptionHandler Error: " + exceptionArgs.getMessage());
        return new ResponseEntity<>(
                new ApiExceptionResponse(
                        exceptionArgs.getMessage(),
                        exceptionArgs.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Object> IllegalArgumentException(IllegalArgumentException exception) {
        setMessage(exception.getMessage());
        ApiRequestException exceptionArgs = new ApiRequestException(
                message,
                HttpStatus.BAD_REQUEST);
        System.out.println("DataParsingExceptionHandler Error: " + exceptionArgs.getMessage());
        return new ResponseEntity<>(
                new ApiExceptionResponse(
                        exceptionArgs.getMessage(),
                        exceptionArgs.getTimestamp()),
                HttpStatus.BAD_REQUEST);
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
