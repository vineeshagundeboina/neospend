package com.neomaxer.neospend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GenericControllerAdvice {
	@ExceptionHandler({ GenericException.class, RecordNotFoundException.class })
	public ResponseEntity<ErrorResponse> handleForbiddenException(Exception e) {
		HttpStatus status = HttpStatus.BAD_REQUEST; // 400
		e.printStackTrace();
//		StringWriter stringWriter = new StringWriter();
//		PrintWriter printWriter = new PrintWriter(stringWriter);
//		e.printStackTrace(printWriter);
//		String stackTrace = stringWriter.toString();

		return new ResponseEntity<>(new ErrorResponse(status.value(), status.getReasonPhrase(), e.getMessage()),
				status);

	}

}
