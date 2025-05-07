package com.dantesoft.siremono.internal.exception;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import com.dantesoft.siremono.internal.exception.DomainExceptions.UnauthorizedException;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class DomainExceptionHandler {

	@Data
	private static class ErrorResponse {
		private final LocalDateTime timestamp;
		private final int status;
		private final String error;
		private final String message;
		private final String path;
	}

	@Data
	@AllArgsConstructor
	private class ValidationErrorOutput {
		private String message;
		private Map<String, List<String>> fields;
	}

	@Data
	@AllArgsConstructor
	@NotBlank
	private class GenericErrorOutput {
		private String message;
		private String details;
	}

	private ResponseEntity<ErrorResponse> buildErrorResponse(DomainExceptions.HttpException ex, HttpStatus status,
			WebRequest request) {
		return ResponseEntity.status(status).body(new ErrorResponse(LocalDateTime.now(), status.value(),
				status.getReasonPhrase(), ex.getMessage(), getRequestPath(request)));
	}

	private String getRequestPath(WebRequest request) {
		return request.getDescription(false).replace("uri=", "");
	}

	@ExceptionHandler(DomainExceptions.HttpException.class)
	public ResponseEntity<ErrorResponse> handleHttpException(DomainExceptions.HttpException ex, WebRequest request) {
		return buildErrorResponse(ex, ex.getStatus(), request);
	}

	@ExceptionHandler(DomainExceptions.NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(DomainExceptions.NotFoundException ex,
			WebRequest request) {
		log.error("Recurso no encontrado: {}", ex.getMessage());
		return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
	}

	@ExceptionHandler(DomainExceptions.ConflictException.class)
	public ResponseEntity<ErrorResponse> handleConflictException(DomainExceptions.ConflictException ex,
			WebRequest request) {
		log.error("Conflicto en la operación: {}", ex.getMessage());
		return buildErrorResponse(ex, HttpStatus.CONFLICT, request);
	}

	@ExceptionHandler(DomainExceptions.ValidationException.class)
	public ResponseEntity<ErrorResponse> handleValidationException(DomainExceptions.ValidationException ex,
			WebRequest request) {
		log.warn("Error de validación: {}", ex.getMessage());
		return buildErrorResponse(ex, HttpStatus.BAD_REQUEST, request);
	}

	@ExceptionHandler(DomainExceptions.OperationException.class)
	public ResponseEntity<ErrorResponse> handleOperationException(DomainExceptions.OperationException ex,
			WebRequest request) {
		log.error("Error interno: {}", ex.getMessage(), ex);
		return buildErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
	}

	@ExceptionHandler(HandlerMethodValidationException.class)
	public ResponseEntity<ValidationErrorOutput> handleValidationException(HandlerMethodValidationException ex) {
		Map<String, List<String>> fields = ex.getAllErrors().stream()
				.collect(Collectors.groupingBy(error -> ((FieldError) error).getField(),
						Collectors.mapping(error -> error.getDefaultMessage(), Collectors.toList())));

		ValidationErrorOutput response = new ValidationErrorOutput("Field has errors", fields);
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorOutput> handleValidationException(
			org.springframework.web.bind.MethodArgumentNotValidException ex) {
		Map<String, List<String>> errors = ex.getBindingResult().getFieldErrors().stream()
				.collect(Collectors.groupingBy(FieldError::getField,
						Collectors.mapping(FieldError::getDefaultMessage, Collectors.toList())));

		ValidationErrorOutput response = new ValidationErrorOutput("Invalid form", errors);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ValidationErrorOutput> handleMissingParamException(
			MissingServletRequestParameterException ex) {
		Map<String, List<String>> fields = Map.of(ex.getParameterName(), List.of("Field is missing"));
		ValidationErrorOutput response = new ValidationErrorOutput("missing required field", fields);
		return ResponseEntity.badRequest().body(response);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ValidationErrorOutput> handleBadCredentialsException(BadCredentialsException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ValidationErrorOutput("Invalid credenctials", Collections.emptyMap()));
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<String> handleUnauthorized(RuntimeException ex) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(ex.getMessage());
	}

}