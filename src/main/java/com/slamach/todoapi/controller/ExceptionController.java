package com.slamach.todoapi.controller;

import com.slamach.todoapi.exception.AuthenticationException;
import com.slamach.todoapi.exception.PermissionDeniedException;
import com.slamach.todoapi.exception.TaskNotFoundException;
import com.slamach.todoapi.exception.UserAlreadyExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Objects;

@RestControllerAdvice
public class ExceptionController {
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<String> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage());
  }

  @ExceptionHandler(TaskNotFoundException.class)
  public ResponseEntity<String> handleTaskNotFoundException(TaskNotFoundException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  @ExceptionHandler(UserAlreadyExistsException.class)
  public ResponseEntity<String> handleUserAlreadyExistsException(UserAlreadyExistsException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  @ExceptionHandler(AuthenticationException.class)
  public ResponseEntity<String> handleAuthenticationException(AuthenticationException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }

  @ExceptionHandler(PermissionDeniedException.class)
  public ResponseEntity<String> handlePermissionDeniedException(PermissionDeniedException exception) {
    return ResponseEntity
        .status(HttpStatus.BAD_REQUEST)
        .body(exception.getMessage());
  }
}
