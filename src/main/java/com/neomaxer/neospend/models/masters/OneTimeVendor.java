package com.neomaxer.neospend.models.masters;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OneTimeVendor extends BaseEntity {
    @Column(nullable=false)
	private String name;
    
    @Column(nullable=false)
	private String accountNumber;
    
    @Column(nullable=false)
	private String mobileNumber;

}
