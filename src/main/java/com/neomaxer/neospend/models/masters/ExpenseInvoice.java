package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Entity;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ExpenseInvoice extends BaseEntity {

	private String description;

	private Date expenseDate;
	
	private BigDecimal requestAmount;
	

}
