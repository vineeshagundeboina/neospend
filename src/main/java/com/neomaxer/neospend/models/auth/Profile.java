package com.neomaxer.neospend.models.auth;

import java.util.Collection;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.neomaxer.neospend.models.common.BaseEntity;
import com.neomaxer.neospend.models.masters.Company;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
public class Profile extends BaseEntity {
	private String firstName;

	private String lastName;

	private String phone;

	private String email;

	private String PAN;

	@Column(columnDefinition = "boolean default false")
	private boolean companyAdmin;

	@ManyToOne
	private Company company;

	@OneToOne
	private User user;

	private String mpin;

	@ManyToMany
	@JoinTable(name = "profile_roles", joinColumns = @JoinColumn(name = "profile_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	

}
