
package com.neomaxer.neospend.models.masters;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

public class ApplicationDetails extends BaseEntity {

	private String locationOfApplicant;

	@Column(nullable = false)
	private String nameOfApplicant;

	private String panNumber;

	@Enumerated(value = EnumType.STRING)
	private ConstitutionType constitutionType;

	@Column
	private boolean isPanAvailable;

	@Column
	private boolean isPanLinked;

	@Column
	private boolean isVendorNonResident;

}
