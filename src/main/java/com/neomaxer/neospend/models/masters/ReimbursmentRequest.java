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
import javax.persistence.OneToOne;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ReimbursmentRequest extends BaseEntity {

	private Date eventCompletionDate;

//	@Column(nullable = false)
//	public String reimbursmentStatus;
	
	@Enumerated(value = EnumType.STRING)
	private RequestStatus requestStatus;

	@Column(nullable = false)
	public BigDecimal reimbursmentAmount;

	@Column(columnDefinition = "boolean default false ")
	public boolean isReject;

	@Column(columnDefinition = "boolean default false")
	public boolean isForward;

	@Column(columnDefinition = "boolean default false")
	private boolean isApprove;

	private String requestNotes;

	@Enumerated(value = EnumType.STRING)
	private ExpenseType expenseType;

	@ElementCollection
	private List<String> attachments;

	@ManyToOne
	private Workflow workflow;

	@ManyToOne
	private DelegationRole delegationRole;

	@OneToOne
	private SanctionRequest sanctionRequest;

}
