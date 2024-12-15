package com.neomaxer.neospend.models.masters;

import javax.persistence.Cacheable;
import javax.persistence.Column;
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
public class Setting extends BaseEntity {

	private String settingKey;

	@Column(columnDefinition = "TEXT")
	private String settingValue;

	private String settingDescription;

	
//	@ManyToOne
//	private Distributor distributor;

}
