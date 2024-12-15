package com.neomaxer.neospend.pojos.requests;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class LoginRequest {
	private String mobileNumber;
	private String type;
	private String email;
	private String password;
}
