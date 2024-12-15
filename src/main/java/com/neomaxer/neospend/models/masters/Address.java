package com.neomaxer.neospend.models.masters;

import javax.persistence.Cacheable;
import javax.persistence.Entity;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
public class Address extends BaseEntity {

	private String billingName;

	private String billingAddress;

	private int pincode;

	private String city;

//	@ManyToOne
//	private State state;

	private boolean isShippingAddressSameAsBillingAddress;

	private String shippingName;

	private String shippingAddress;

//	@Column
//	public String line1;
//
//	@Column
//	public String line2;
//
//	@Column
//	public String city;
//

//
//	@ManyToOne
//	public State state;
//
//	@Column
//	public String label;
//
//	@Column(columnDefinition = "float8 default 0.0")
//    private double latitude;
//
//    @Column(columnDefinition = "float8 default 0.0")
//    private double longitude;
//
//	@ManyToOne
//	public Country country;
//
//	@ManyToOne
//	public Customer customer;
//
//	@ManyToOne
//	public Vendor vendor;
//
//	@ManyToOne
//	public Company company;
//
//	@ManyToOne
//	public Store store;
//
//	@ManyToOne
//	public Warehouse warehouse;
//
//	public String landmark;

}
