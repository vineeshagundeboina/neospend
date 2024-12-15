package com.neomaxer.neospend.models.auth;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

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
public class Privilege extends BaseEntity {

	private String name;
	
	private String controllerIdentifier;
	
	private String methodIdentifier;

	private String description;
	
	@Column(columnDefinition = "boolean default true")
	private boolean allowAll;

	private String apiPath;
	
	@ManyToMany(mappedBy = "privileges")
	private Collection<Role> roles;
	
}
