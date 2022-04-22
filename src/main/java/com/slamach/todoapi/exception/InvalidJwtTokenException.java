package com.slamach.todoapi.exception;

public class InvalidJwtTokenException extends RuntimeException {
  public InvalidJwtTokenException(String message) {
    super(message);
  }
}
