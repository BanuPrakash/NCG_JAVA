package com.adobe.orderapp.api;

import com.adobe.orderapp.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException ex) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("timestamp", new Date());
        data.put("status", "404");
        data.put("message", ex.getMessage());
        return new  ResponseEntity<Object>(data, HttpStatus.NOT_FOUND); //404
    }
}
