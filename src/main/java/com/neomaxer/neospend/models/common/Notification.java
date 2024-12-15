package com.neomaxer.neospend.models.common;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.neomaxer.neospend.models.auth.Role;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Notification extends BaseEntity {

	private String summary;

	private String detail;

	private String severity;

	@ManyToOne
	private Role role;

	@Column(columnDefinition = "boolean default false")
	private boolean readStatus;
}
