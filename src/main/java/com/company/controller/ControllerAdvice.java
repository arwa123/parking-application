package com.company.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Optional;

@org.springframework.web.bind.annotation.ControllerAdvice
@RequestMapping(produces = "application/vnd.error+json")
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> notFoundException(final Exception e) {
        return error(e, HttpStatus.NOT_FOUND, e.getMessage());
    }


    private ResponseEntity<?> error(
            final Exception exception, final HttpStatus httpStatus, final String logRef) {
        final String message =
                Optional.of(exception.getMessage()).orElse(exception.getClass().getSimpleName());
        return new ResponseEntity<>(new Exception(message), httpStatus);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> assertionException(final IllegalArgumentException e) {
        return error(e, HttpStatus.NOT_FOUND, e.getLocalizedMessage());
    }
}