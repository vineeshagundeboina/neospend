package com.neomaxer.neospend.models.masters;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Company")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Company extends BaseEntity {

	@Column(unique = true)
	private String companyName;

	private String companyLocation;

	private String companyPanNumber;

	private String companyAddress1;

	private String companyAddress2;

	private String companyPhone;

	private String companyMail;

	private String pincode;

	@Column(columnDefinition = "boolean default false")
	private boolean subscriptionActive;

	private Date subscriptionActivationDate;

	private Date subscriptionValidTill;

	private Long subscriptionMonths;

	private Long numberOfStores;

	private Long numberOfDistributors;

	@ManyToOne
	private Country country;

	private String dealerCode;

}
