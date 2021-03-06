package com.jctiru.lnshop.api.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jctiru.lnshop.api.ui.model.response.ErrorResponse;

@ControllerAdvice
public class AppExceptionsHandler extends ResponseEntityExceptionHandler {

	@Override
	public final ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Map<String, String>> details = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.add(Collections.singletonMap(error.getField(), error.getDefaultMessage()));
		}

		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Validation Errors",
				details);

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@Override
	public final ResponseEntity<Object> handleBindException(
			BindException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		List<Map<String, String>> details = new ArrayList<>();

		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			details.add(Collections.singletonMap(error.getField(), error.getDefaultMessage()));
		}

		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Validation Errors",
				details);

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(RecordNotFoundException.class)
	public ResponseEntity<Object> handleRecordNotFound(RecordNotFoundException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND, LocalDateTime.now(), "Record doesn't exists",
				Collections.singletonMap("error", ex.getMessage()));

		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(RecordAlreadyExistsException.class)
	public ResponseEntity<Object> handleRecordAlreadyExists(RecordAlreadyExistsException ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.CONFLICT, LocalDateTime.now(), "Record already exists",
				Collections.singletonMap("error", ex.getMessage()));

		return new ResponseEntity<>(error, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<Object> handleOtherExceptions(Exception ex, WebRequest request) {
		ErrorResponse error = new ErrorResponse(HttpStatus.BAD_REQUEST, LocalDateTime.now(), "Error",
				Collections.singletonMap("error", ex.getMessage()));

		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}

}
