package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.neomaxer.neospend.models.auth.Role;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class TemporaryInvoice extends BaseEntity {

	@Column(unique = true)
	private String invoiceNo;

	private String docStatus;

	private String docAction;

	private boolean processing;

	private boolean processed;

	private boolean posted;

	private Date invoicedate;

	@ManyToOne
	private Role Customer;

	private String vendorCustomerLocation;

	private String currency = "Rupees";

	private BigDecimal totalAmount;

	private BigDecimal tdsAmount;

	private BigDecimal sgstAmount;

	private BigDecimal cgstAmount;

	private BigDecimal igstAmount;

	private BigDecimal ugstAmount;

	private boolean isSendToLedger;

	private UUID PaymentRequestID;

	private int invoiceSequence;

}
