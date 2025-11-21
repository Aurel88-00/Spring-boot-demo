package com.example.springdemo.common.exception;

import java.time.Instant;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.springdemo.author.exception.AuthorNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AuthorNotFoundException.class)
	public ResponseEntity<Map<String, Object>> handleAuthorNotFound(AuthorNotFoundException exception) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(Map.of(
						"timestamp", Instant.now(),
						"error", exception.getMessage()
				));
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, Object>> handleValidation(MethodArgumentNotValidException exception) {
		var errors = exception.getBindingResult()
				.getFieldErrors()
				.stream()
				.collect(java.util.stream.Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage, (l, r) -> r));

		return ResponseEntity.badRequest()
				.body(Map.of(
						"timestamp", Instant.now(),
						"errors", errors
				));
	}
}

