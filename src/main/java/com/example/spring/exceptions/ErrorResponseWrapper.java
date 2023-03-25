package com.example.spring.exceptions;

import com.example.spring.exceptions.StatusException;
import lombok.AllArgsConstructor;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.lang.reflect.InvocationTargetException;

@AllArgsConstructor
@ControllerAdvice(annotations = RestController.class)
public class ErrorResponseWrapper {
    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity handleDefaultErrors(ResponseStatusException ex) {
        StatusException statusException = new StatusException(ex.getReason(), ex.getStatusCode().value());
        return new ResponseEntity<>(statusException, ex.getStatusCode());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleConstraint(DataIntegrityViolationException ex) {
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        StatusException errorMessage = new StatusException(message, HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
}
