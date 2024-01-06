package com.mkdk.schedule.controller;

import com.mkdk.schedule.exception.ScheduleNotFoundException;
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

  @ExceptionHandler(value = ScheduleNotFoundException.class)
  public ResponseEntity<Map<String, String>> handleNoScheduleFound(
      ScheduleNotFoundException e, HttpServletRequest request) {
    Map<String, String> body = Map.of(
        "timestamp", ZonedDateTime.now().toString(),
        "status", String.valueOf(HttpStatus.NOT_FOUND.value()),
        "error", HttpStatus.NOT_FOUND.getReasonPhrase(),
        "message", e.getMessage(),
        "path", request.getRequestURI());
    return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);

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
