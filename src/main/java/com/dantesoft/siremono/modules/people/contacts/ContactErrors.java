package com.dantesoft.siremono.modules.people.contacts;

import java.util.UUID;

import com.dantesoft.siremono.internal.exception.DomainExceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ContactErrors {
  private static final String NOT_FOUND_MESSAGE = "Contact not found with id {}";
  private static final String ALREADY_EXIST_MESSAGE = "Contact with name {} already exists";
  private static final String VALIDATION_MESSAGE = "Cant continue cause the input its not valid: \n {}";
  private static final String OPERATION_FAILED_MESSAGE = "The operation \"{}\" has failed";

  public static class NotFoundException extends DomainExceptions.NotFoundException {

    public NotFoundException(UUID id) {
      super(NOT_FOUND_MESSAGE.replace("{}", id.toString()));
      log.warn(NOT_FOUND_MESSAGE, id);
    }

  }

  public static class AlreadyExistsException extends DomainExceptions.ConflictException {

    public AlreadyExistsException(String name) {
      super(ALREADY_EXIST_MESSAGE.replace("{}", name));
      log.warn(ALREADY_EXIST_MESSAGE, name);
    }
  }

  public static class ValidationException extends DomainExceptions.ValidationException {

    public ValidationException(String message) {
      super(VALIDATION_MESSAGE.replace("{}", message));
      log.warn(VALIDATION_MESSAGE, message);
    }
  }

  public static class OperationException extends DomainExceptions.OperationException {

    public OperationException(String operation) {
      super(OPERATION_FAILED_MESSAGE.replace("{}", operation));
      log.warn(OPERATION_FAILED_MESSAGE, operation);
    }
  }
}
