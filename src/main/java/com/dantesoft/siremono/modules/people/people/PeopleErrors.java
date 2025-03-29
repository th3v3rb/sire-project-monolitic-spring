package com.dantesoft.siremono.modules.people.people;

import java.util.UUID;

import com.dantesoft.siremono.internal.exception.DomainExceptions;

public class PeopleErrors {
  private static final String NOT_FOUND_MESSAGE = "Person not found with id {}";
  private static final String ALREADY_EXIST_MESSAGE = "Person with name {} already exists";
  private static final String HAS_ASSOCIACION_MESSAGE = "Cannot delete Person with id: {} because it has associated products";
  private static final String VALIDATION_MESSAGE = "Cant continue cause the input its not valid: \n {}";
  private static final String OPERATION_FAILED_MESSAGE = "The operation \"{}\" has failed";

  private PeopleErrors() {
  }

  public static class NotFoundException extends DomainExceptions.NotFoundException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(UUID id) {
      super(NOT_FOUND_MESSAGE.replace("{}", id.toString()));
    }

    public NotFoundException(String message, UUID id) {
      super("$1 - $2".replace("$1", message).replace("$2", id.toString()));
    }

  }

  public static class AlreadyExistsException extends DomainExceptions.ConflictException {
    private static final long serialVersionUID = 1L;

    public AlreadyExistsException(String name) {
      super(ALREADY_EXIST_MESSAGE.replace("{}", name));
    }
  }

  public static class HasAssociatedProductsException extends DomainExceptions.ConflictException {
    private static final long serialVersionUID = 1L;

    public HasAssociatedProductsException(UUID id) {
      super(HAS_ASSOCIACION_MESSAGE.replace("{}", id.toString()));
    }
  }

  public static class ValidationException extends DomainExceptions.ValidationException {
    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
      super(VALIDATION_MESSAGE.replace("{}", message));
    }
  }

  public static class OperationException extends DomainExceptions.OperationException {
    private static final long serialVersionUID = 1L;

    public OperationException(String operation) {
      super(OPERATION_FAILED_MESSAGE.replace("{}", operation));
    }
  }
}
