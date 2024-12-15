package com.neomaxer.neospend.pojos.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupResponse {
	Boolean status = false;
	String message = "";
	Boolean isProfileExists = false;
	Boolean isMPINExists = false;


}