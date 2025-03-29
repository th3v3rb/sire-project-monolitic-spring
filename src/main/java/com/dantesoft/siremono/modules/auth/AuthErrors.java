package com.dantesoft.siremono.modules.auth;

import java.util.UUID;

import com.dantesoft.siremono.internal.exception.DomainExceptions;

public class AuthErrors implements DomainExceptions {
  private static final String NOT_FOUND_MESSAGE = "Not found element with id {}";
  private static final String NOT_FOUND_MESSAGE_NO_ID = "Not found element with message: \"{}\"";
  private static final String ALREADY_EXIST_MESSAGE = "Element already exists with identifier {}";
  private static final String OPERATION_FAILED_MESSAGE = "The operation \"{}\" has failed";

  public static class NotFoundException extends DomainExceptions.NotFoundException {
    public NotFoundException(UUID id) {
      super(NOT_FOUND_MESSAGE.replace("{}", id.toString()));
    }

    public NotFoundException(String message) {
      super(NOT_FOUND_MESSAGE_NO_ID.replace("{}", message));
    }
  }

  public static class AlreadyExistsException extends DomainExceptions.ConflictException {
    public AlreadyExistsException(String name) {
      super(ALREADY_EXIST_MESSAGE.replace("{}", name));
    }
  }

  public static class ValidationException extends DomainExceptions.ValidationException {
    public ValidationException(String message) {
      super(message);
    }
  }

  public static class OperationException extends DomainExceptions.OperationException {
    public OperationException(String operation) {
      super(OPERATION_FAILED_MESSAGE.replace("{}", operation));
    }
  }
}
