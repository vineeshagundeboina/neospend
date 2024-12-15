package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity(name = "TaxRate")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class TaxRate extends BaseEntity {

	private String taxRateName;

	

	private BigDecimal gst;

	private BigDecimal igst;

	private BigDecimal cgst;

	private BigDecimal sgst;

	private BigDecimal utgst;

}
