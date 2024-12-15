package com.neomaxer.neospend.pojos.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AuthenticationResponse {

	private String jwt;

	private String refreshToken;

	private String tokenType = "Bearer";

	public AuthenticationResponse(String jwt, String refreshToken) {
		super();
		this.jwt = jwt;
		this.refreshToken = refreshToken;
	}

}
