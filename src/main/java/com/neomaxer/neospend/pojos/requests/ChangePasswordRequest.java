package com.neomaxer.neospend.pojos.requests;

import com.neomaxer.neospend.models.auth.User;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ChangePasswordRequest {
	
	private String newPassword;
	
	private User user;

}

