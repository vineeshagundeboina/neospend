package com.neomaxer.neospend.models.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SendOtp {
	
	private String mobile;
	private String fcmToken;
	private String username;

}