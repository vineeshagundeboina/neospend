package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.neomaxer.neospend.models.auth.Role;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "expense_history")
public class ExpenseHistory extends BaseEntity {

	@Enumerated(value = EnumType.STRING)
	private RequestStatus requestStatus;

	private BigDecimal requestAmount;

	private String requestNotes;

	@ManyToOne
	@JoinColumn(name = "maker_role_id")
	private Role makerRole;

	@ManyToOne
	@JoinColumn(name = "checker_role_id")
	private Role checkerRole;

	@ManyToOne
	@JoinColumn(name = "expense_request_id")
	private ExpenseRequest expenseRequest;
	
	@ManyToOne
	@JoinColumn(name = "sanction_request_id")
	private SanctionRequest sanctionRequest;
	
	@ManyToOne
	@JoinColumn(name = "reimbursment_request_id")
	private ReimbursmentRequest reimbursmentRequest;

	




}
