package com.neomaxer.neospend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DataIntegrityControllerAdvice {

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<ErrorResponse> handleForbiddenException(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		e.printStackTrace();
		return new ResponseEntity<>(
				new ErrorResponse(status.value(), status.getReasonPhrase(), "Data issue please check logs"),
				status);

	}

}
