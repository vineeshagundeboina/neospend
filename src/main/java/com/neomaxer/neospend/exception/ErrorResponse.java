package com.neomaxer.neospend.exception;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse {
	private Date timestamp = new Date();
	private int code;
	private String status;
	private String message;

	public ErrorResponse(int code, String status, String message) {
		super();
		this.code = code;
		this.status = status;
		this.message = message;
	}

}
