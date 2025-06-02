package com.dantesoft.siremono.internal.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class DomainExceptionHandler {

  @Data
  @AllArgsConstructor
  public static class ErrorResponseDTO {
    private Map<String, List<ErrorMessageDTO>> errors;
  }

  @Data
  @AllArgsConstructor
  public static class ErrorMessageDTO {
    private String message;
    private String error;
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponseDTO> handle(MethodArgumentNotValidException e) {
    log.info("Handling MethodArgumentNotValidException");

    Map<String, List<ErrorMessageDTO>> errorsGrouped = e.getBindingResult()
        .getFieldErrors()
        .stream()
        .collect(Collectors.groupingBy(
            FieldError::getField,
            Collectors.mapping(
                fe -> new ErrorMessageDTO(
                    fe.getDefaultMessage(),
                    fe.getCode()
                ),
                Collectors.toList()
            )
        ));

    var errorDTO = new ErrorResponseDTO(errorsGrouped);

    return ResponseEntity
        .status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(errorDTO);
  }

}