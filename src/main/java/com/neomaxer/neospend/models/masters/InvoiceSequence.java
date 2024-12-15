package com.neomaxer.neospend.models.masters;

import javax.persistence.Entity;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class InvoiceSequence extends BaseEntity {

	private String prefix;

	private Long sequenceNumber;

}
