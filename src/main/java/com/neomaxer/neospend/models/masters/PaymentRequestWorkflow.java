package com.neomaxer.neospend.models.masters;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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

public class PaymentRequestWorkflow extends BaseEntity {

	@ManyToOne
	private Role makerRole;

	@ManyToOne
	private Role checkerRole;

	private String description;

	@Enumerated(value = EnumType.STRING)
	private CompanyType companyType;

	@ManyToOne
	private WorkflowItem workflow;

	@Override
	public String toString() {
		return this.makerRole + "," + this.checkerRole + "," + this.description;

	}
}
