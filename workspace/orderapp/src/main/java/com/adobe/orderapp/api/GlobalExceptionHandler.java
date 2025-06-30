package com.adobe.orderapp.api;

import com.adobe.orderapp.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("timestamp", new Date());
        List<String> errors = ex.getBindingResult()
                .getFieldErrors().stream().map(exception -> exception.getDefaultMessage())
                .collect(Collectors.toList());
        data.put("errors", errors);
        return new  ResponseEntity<Object>(data, HttpStatus.BAD_REQUEST); //400
    }
}
