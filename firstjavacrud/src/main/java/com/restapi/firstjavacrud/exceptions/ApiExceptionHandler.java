package com.restapi.firstjavacrud.exceptions;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApiExceptionHandler {
    private HttpStatus badRequest = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(value = { ApiRequestException.class })
    public ResponseEntity<Object> handleApiExceptionHandler(ApiRequestException e) {
        ApiException apiException = new ApiException(e.getLocalizedMessage(),
                // e,
                badRequest,
                ZonedDateTime.now(ZoneId.of("America/Sao_Paulo")));
        return new ResponseEntity<>(apiException, badRequest);
    }
}