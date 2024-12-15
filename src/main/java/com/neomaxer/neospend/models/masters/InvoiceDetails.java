package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

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

public class InvoiceDetails extends BaseEntity {
	@Column
	public String invoiceNo;

	public Date invoiceDate;

	@Column
	private BigDecimal gst;

	private BigDecimal tdsAmount;

	private BigDecimal netAmount;

	@Enumerated(value = EnumType.STRING)
	private RequestType requestType;

	@Column
	private boolean singlePaymentForFy;

}