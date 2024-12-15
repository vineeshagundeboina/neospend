package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class SanctionRequest extends BaseEntity {

	@Column(nullable = false)
	private String userName;

	@Column
	private String email;

	@Column
	private String mobileNumber;

	@Enumerated(value = EnumType.STRING)
	private ExpenseType expenseType;

	@Enumerated(value = EnumType.STRING)
	private RequestType Type;

	@Column(nullable = false)
	public BigDecimal sanctionAmount;

	

	public boolean isReject;

	public boolean isForward;

	private boolean isApprove;

	private String notes;

	private Date startdate;

	private Date endDate;

	@ElementCollection
	private List<String> attachments;

	@ManyToOne
	private DelegationRole delegationRole;

	@ManyToOne
	private Workflow workflow;

	@Enumerated(value = EnumType.STRING)
	private RequestStatus requestStatus;


}
