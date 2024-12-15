package com.neomaxer.neospend.models.masters;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.cache.annotation.Cacheable;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Cacheable
public class AccountDetails extends BaseEntity {

	@Column
	private String AccountType;

	@Column
	private String AccountNumber;

	private String confirmAccountNumber;

	@Column
	private String IFSC;

	private String bankname;

}
