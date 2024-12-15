package com.neomaxer.neospend.models.masters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Vendor extends BaseEntity {
	@Column(nullable=false)
	private String firstName;
	
	@Column(nullable=false)
	private String lastName;
	
	@Column(nullable=false)
	private String mobileNumber;
	
	private String email;
	
	private String upiId;
	
	@ManyToOne
	private AccountDetails accountDetails;
	
	@ManyToOne
	private Address address;
	
	private String notes;

}
