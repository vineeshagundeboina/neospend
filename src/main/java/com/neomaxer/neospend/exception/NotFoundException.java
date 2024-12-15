package com.neomaxer.neospend.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

	public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public HttpStatus getHttpStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
