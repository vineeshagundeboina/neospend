package com.neomaxer.neospend.controllers.auth;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
public class SignupRequest {
	private String companyName;
	private String mobileNumber;
	private String email;
	private String password;
	private String type;
	private String panNumber;
    private String partnerCode;
}
