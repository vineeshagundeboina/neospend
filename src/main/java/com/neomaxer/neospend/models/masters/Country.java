package com.neomaxer.neospend.models.masters;

import javax.persistence.Cacheable;
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
public class Country extends BaseEntity {

	public String countryName;

	public String countryCode;

}
