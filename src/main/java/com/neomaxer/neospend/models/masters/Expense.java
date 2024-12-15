package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;

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

public class Expense extends BaseEntity {

	
	public String name;

	private String description;

	@Enumerated(value = EnumType.STRING)
	private ExpenseType expenseType;

	@Column(nullable = false)
	private boolean needapproval;

	private BigDecimal mrp = new BigDecimal(0);

	@ManyToOne
	private ExpenseCategory expenseCategory;

	@ManyToOne
	private TaxRate taxRate;

	@Override
	public String toString() {
		return this.name + "," + this.expenseType + "," + this.description;
	}

}
