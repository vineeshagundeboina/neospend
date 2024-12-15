package com.neomaxer.neospend.pojos.requests;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class TokenRefreshRequest {

	@NotBlank
	private String refreshToken;

}
