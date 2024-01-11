package com.mkdk.schedule.controller;

import com.mkdk.schedule.exception.ResourceExistsException;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ScheduleExceptionHandler {

  @ExceptionHandler(value = ResourceNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNoResourceFound(
      ResourceNotFoundException e, HttpServletRequest request) {
    Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
        "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
        "message", e.getMessage(),
        "path", request.getRequestURI());
    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = ResourceExistsException.class)
  public ResponseEntity<Map<String, String>> handleResourceExists(
      ResourceExistsException e, HttpServletRequest request) {
    Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.CONFLICT.value()),
        "error", HttpStatus.CONFLICT.getReasonPhrase(),
        "message", e.getMessage(),
        "path", request.getRequestURI());
    return new ResponseEntity<>(body, HttpStatus.CONFLICT);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, Object>> handleValidationException(
      MethodArgumentNotValidException ex, HttpServletRequest request) {
    Map<String, String> errors = new HashMap<>();
    for (FieldError e : ex.getFieldErrors()) {
      errors.put(e.getField(), e.getDefaultMessage());
    }
    Map<String, Object> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.BAD_REQUEST.value()),
        "error", HttpStatus.BAD_REQUEST.getReasonPhrase(),
        "message", errors,
        "path", request.getRequestURI());
    return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
  }

}
