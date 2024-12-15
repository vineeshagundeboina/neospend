package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import com.neomaxer.neospend.models.auth.Profile;
import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EntityListeners(ExpenseRequestEntityListener.class)
@Entity
@Getter
@Setter
@NoArgsConstructor

public class ExpenseRequest extends BaseEntity {

	private String description;

	private Date ExpenseDate;

	private Date DueDate;

	@Enumerated(value = EnumType.STRING)
	private RequestStatus requestStatus;

	@Column(nullable = false)
	public BigDecimal requestAmount;

	@Column
	public BigDecimal advanceAmount;

	@ManyToOne
	private TaxRate taxRate;

	@ManyToOne
	private ExpenseCategory expenseCategory;

	private String requestNotes;

	@ElementCollection
	private List<String> attachments;

	@OneToOne
	private Vendor vendor;

	@ManyToOne
	private Profile profile;

	@ManyToOne
	private Workflow workflow;

	@OneToOne
	private SanctionRequest sanctionRequest;

}
