package com.neomaxer.neospend.models.auth;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VerifyOtp {
	
	private String mobileNumber;
	private int otp;

}

