package com.slamach.todoapi.exception;

public class PermissionDeniedException extends RuntimeException {
  public PermissionDeniedException(String message) {
    super(message);
  }
}
