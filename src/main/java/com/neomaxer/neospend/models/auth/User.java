package com.neomaxer.neospend.models.auth;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User extends BaseEntity {

	@Column(unique = true)
	// @Size(max=12,min=10,message="Mobile should be 10 digit")
	private String mobileNumber;

	@Column(unique = true)
	private String userName;

	@Column(unique = true)
	// @Email(message ="email should be proper format" )
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;

	@Column(columnDefinition = "BOOLEAN DEFAULT TRUE")
	private boolean deleteAllowed = true;

	private String fcmToken;

	private String mpin;

	@Column(columnDefinition = "boolean default false")
	private boolean emailVerified;

	@Column(columnDefinition = "boolean default false")
	private boolean mobileVerified;
	
	@ManyToOne
	private Profile profile;

	@ManyToMany
	@JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
	private Collection<Role> roles;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String resetToken;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String activationToken;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date resetTokenExpiryDate;

	@JsonProperty(access = Access.WRITE_ONLY)
	private Date emailTokenExpiryDate;
}
