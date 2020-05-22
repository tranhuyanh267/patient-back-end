package com.healthsoft.configs;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<FieldErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {

        List<FieldError> errorResources = exception.getBindingResult().getFieldErrors().stream().map(fieldError ->
                new FieldError(
                        fieldError.getField(),
                        fieldError.getDefaultMessage(),
                        fieldError.getCode()
                )).collect(Collectors.toList());

        FieldErrorResponse error = new FieldErrorResponse(errorResources);

        return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<ExceptionResponse> handleResponseStatusException(ResponseStatusException exception) {
        ExceptionResponse error = new ExceptionResponse();
        error.setMessage(exception.getMessage());
        return new ResponseEntity<>(error, exception.getStatus());
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(ConstraintViolationException exception) {
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }
}
