package com.restapi.firstjavacrud.exceptions;

import java.sql.SQLException;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.hibernate.exception.JDBCConnectionException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class DataParsingExceptionHandler {
        String message;
        String listOfErrors;

        /**
         * This function is called when an MethodArgumentTypeMismatchException is
         * thrown and returns a String with the and returns a new ResponseEntity
         * 
         * @param exception The exception object that was thrown
         * @return A ResponseEntity object.
         */
        @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
        public ResponseEntity<Object> MethodArgumentTypeMismatchException(
                        MethodArgumentTypeMismatchException exception) {
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

        @ExceptionHandler({ JDBCConnectionException.class })
        public ResponseEntity<Object> JDBCConnectionException(SQLException exception) {
                Throwable cause = exception;
                while (cause.getCause() != null) {
                        cause = cause.getCause();
                }
                System.out.println("DataParsingExceptionHandler Error: " +
                                cause.getSuppressed());
                setMessage(cause.getMessage());
                ApiRequestException exceptionArgs = new ApiRequestException(
                                message,
                                HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(
                                new ApiExceptionResponse(
                                                exceptionArgs.getMessage(),
                                                exceptionArgs.getTimestamp()),
                                HttpStatus.BAD_REQUEST);
        }

        /**
         * This function is called when an IllegalArgumentException is thrown. It sets
         * the message to
         * the exception message, creates a new ApiRequestException with the message and
         * HttpStatus.BAD_REQUEST, and returns a new ResponseEntity with the
         * ApiExceptionResponse and
         * HttpStatus.BAD_REQUEST
         * 
         * @param exception The exception that was thrown
         * @return A ResponseEntity object.
         */
        @ExceptionHandler({ IllegalArgumentException.class, DataIntegrityViolationException.class })
        public ResponseEntity<Object> IllegalArgumentException(Exception exception) {
                System.out.println("DataParsingExceptionHandler Error: " + exception.getMessage());
                setMessage(exception.getMessage());
                ApiRequestException exceptionArgs = new ApiRequestException(
                                message,
                                HttpStatus.BAD_REQUEST);
                return new ResponseEntity<>(
                                new ApiExceptionResponse(
                                                exceptionArgs.getMessage(),
                                                exceptionArgs.getTimestamp()),
                                HttpStatus.BAD_REQUEST);
        }

        /**
         * The function takes a list of ConstraintViolation objects, maps them into a
         * string, and then
         * returns a ResponseEntity object with a list of errors and a timestamp
         * 
         * @param exception The exception that was thrown
         * @param request   The request that triggered the exception
         * @return A ResponseEntity object.
         */
        @ExceptionHandler({ ConstraintViolationException.class })
        public ResponseEntity<Object> ConstraintViolationException(ConstraintViolationException exception,
                        ServletWebRequest request) {

                Set<ConstraintViolation<?>> violationsList = exception.getConstraintViolations();
                String mappedList = mapListOfErrorsIntoString(violationsList);
                setListOfErrors(mappedList);
                ApiRequestException exceptionArgs = new ApiRequestException(
                                message,
                                HttpStatus.BAD_REQUEST);

                return new ResponseEntity<>(
                                new ApiExceptionResponse(
                                                listOfErrors,
                                                exceptionArgs.getTimestamp()),
                                HttpStatus.BAD_REQUEST);
        }

        /**
         * Map and convert a list of errors into a string.
         * 
         * @param listOfErrors A list of errors that are returned by the validation.
         * @return A list of strings.
         */
        public String mapListOfErrorsIntoString(Set<ConstraintViolation<?>> listOfErrors) {
                return listOfErrors.stream()
                                .map(violation -> String.format("The field %s is invalid: %s",
                                                StreamSupport.stream(violation.getPropertyPath().spliterator(), false)
                                                                .reduce((first, second) -> first).orElse(null),
                                                violation.getMessage()))
                                .collect(Collectors.toList()).toString();
        }

        public String getMessage() {
                return message;
        }

        public void setMessage(String message) {
                this.message = message;
        }

        public String getListOfErrors() {
                return listOfErrors;
        }

        public void setListOfErrors(String listOfErrors) {
                this.listOfErrors = listOfErrors;
        }
}
