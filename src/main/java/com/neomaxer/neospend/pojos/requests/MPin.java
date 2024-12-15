package com.neomaxer.neospend.pojos.requests;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MPin {

	private UUID userId;
	private String mpin;

}
