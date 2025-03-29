package com.dantesoft.siremono.internal.exception;

import org.springframework.http.HttpStatus;

import lombok.Getter;

public interface DomainExceptions {

  @Getter
  abstract class HttpException extends RuntimeException {
    private final HttpStatus status;

    protected HttpException(String message, HttpStatus status) {
      super(message);
      this.status = status;
    }
  }

  @Getter
  abstract class NotFoundException extends HttpException {
    protected NotFoundException(String message) {
      super(message, HttpStatus.NOT_FOUND);
    }
  }

  @Getter
  abstract class ConflictException extends HttpException {
    protected ConflictException(String message) {
      super(message, HttpStatus.CONFLICT);
    }
  }

  @Getter
  abstract class ValidationException extends HttpException {

    protected ValidationException(String message) {
      super(message, HttpStatus.BAD_REQUEST);
    }
  }

  @Getter
  abstract class OperationException extends HttpException {
    protected OperationException(String message) {
      super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}