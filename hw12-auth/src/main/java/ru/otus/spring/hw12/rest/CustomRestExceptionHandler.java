package ru.otus.spring.hw12.rest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<String>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.add(getMessage(e.getField(), e.getDefaultMessage())));
        ex.getBindingResult().getGlobalErrors().forEach(e -> errors.add(getMessage(e.getObjectName(),e.getDefaultMessage())));
        ApiError apiError =  new ApiError(HttpStatus.BAD_REQUEST.value(), errors);
        return handleExceptionInternal(ex, apiError, headers, HttpStatus.BAD_REQUEST, request);
    }

    private String getMessage(String name, String error) {
        return name + ": " + error;
    }

}
