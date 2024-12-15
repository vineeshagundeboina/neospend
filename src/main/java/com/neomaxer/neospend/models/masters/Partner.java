package com.neomaxer.neospend.models.masters;

import javax.persistence.Entity;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Partner extends BaseEntity {

	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private String address;
	private String companyName;
	private String panNumber;
	private String gstNumber;
	private String status;
	private String partnerCode;

}
