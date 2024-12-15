package com.neomaxer.neospend.models.masters;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
public class Customer extends BaseEntity {

	private String firstName;

	private String lastName;

	private String phone;

	private String email;

	private String GST;

	private String contactPersonName;

	private String contactPersonPhone;

	private String contactPersonEmail;

	@ManyToOne
	private Company company;

	@ManyToOne
	private User user;

	private String billingAddress1;

	private String billingAddress2;

	private String pincode;

//	@ManyToOne
//	private State state;

//	
//	@ManyToOne
//	private Address billingAddress;
//	
//	@ManyToOne
//	private Address shippingAddress;

	// @OneToMany(mappedBy = "customer")
	// private List<Address> addresses;

}
