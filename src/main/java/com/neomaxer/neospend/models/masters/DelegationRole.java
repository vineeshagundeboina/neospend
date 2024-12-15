package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neomaxer.neospend.models.auth.Role;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class DelegationRole extends BaseEntity {

	@Column
	private String delegationName;

	private String delegationDescription;

	public BigDecimal delegationAmount;

	@ManyToOne
	private Role Role;

}
