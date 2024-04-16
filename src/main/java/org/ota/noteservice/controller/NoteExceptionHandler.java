package org.ota.noteservice.controller;

import lombok.extern.slf4j.Slf4j;
import org.ota.noteservice.data.ErrorResponse;
import org.ota.noteservice.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.HandlerMethod;

import java.io.IOException;

@ControllerAdvice
@Slf4j
public class NoteExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex, HandlerMethod handlerMethod) {
        log.error("Error processing {}: {}", getEndpointInfo(handlerMethod), ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("NotFound");
        errorResponse.setMessage(ex.getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ErrorResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("UnsupportedOperation");
        errorResponse.setMessage("Method not allowed");

        return new ResponseEntity<>(errorResponse, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleDefaultExceptions(Exception ex) {
        log.error(ex.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("UnknownError");
        errorResponse.setMessage("Internal Server Error");

        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private String getEndpointInfo(HandlerMethod handlerMethod) {
        if (handlerMethod != null) {
            return handlerMethod.getMethod().getName();
        }
        return "Unknown Endpoint";
    }
}
