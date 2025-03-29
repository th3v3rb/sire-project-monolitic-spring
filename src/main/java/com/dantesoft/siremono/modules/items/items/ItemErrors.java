package com.dantesoft.siremono.modules.items.items;

import java.util.UUID;

import com.dantesoft.siremono.internal.exception.DomainExceptions;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ItemErrors implements DomainExceptions {
  private static final String NOT_FOUND_MESSAGE = "Category not found with id {}";
  private static final String NOT_FOUND_MESSAGE_NO_ID = "Category not found with message: \"{}\"";
  private static final String ALREADY_EXIST_MESSAGE = "Category with name {} already exists";
  private static final String HAS_ASSOCIACION_MESSAGE = "Cannot delete Category with id: {} because it has associated products";
  private static final String VALIDATION_MESSAGE = "Cant continue cause the input its not valid: \n {}";
  private static final String OPERATION_FAILED_MESSAGE = "The operation \"{}\" has failed";

  public static class NotFoundException extends DomainExceptions.NotFoundException {
    private static final long serialVersionUID = 1L;

    public NotFoundException(UUID id) {
      super(NOT_FOUND_MESSAGE.replace("{}", id.toString()));
      log.warn(NOT_FOUND_MESSAGE, id);
    }

    public NotFoundException(String message) {
      super(NOT_FOUND_MESSAGE_NO_ID.replace("{}", message));
      log.warn(NOT_FOUND_MESSAGE_NO_ID, message);
    }
  }

  public static class AlreadyExistsException extends DomainExceptions.ConflictException {
    private static final long serialVersionUID = 1L;

    public AlreadyExistsException(String name) {
      super(ALREADY_EXIST_MESSAGE.replace("{}", name));
      log.warn(ALREADY_EXIST_MESSAGE, name);
    }
  }

  public static class HasAssociatedProductsException extends DomainExceptions.ConflictException {
    private static final long serialVersionUID = 1L;

    public HasAssociatedProductsException(UUID id) {
      super(HAS_ASSOCIACION_MESSAGE.replace("{}", id.toString()));
      log.warn(HAS_ASSOCIACION_MESSAGE, id);
    }
  }

  public static class ValidationException extends DomainExceptions.ValidationException {
    private static final long serialVersionUID = 1L;

    public ValidationException(String message) {
      super(VALIDATION_MESSAGE.replace("{}", message));
      log.warn(VALIDATION_MESSAGE, message);
    }
  }

  public static class OperationException extends DomainExceptions.OperationException {
    private static final long serialVersionUID = 1L;

    public OperationException(String operation) {
      super(OPERATION_FAILED_MESSAGE.replace("{}", operation));
      log.warn(OPERATION_FAILED_MESSAGE, operation);
    }
  }
}
