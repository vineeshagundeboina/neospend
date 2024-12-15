package com.neomaxer.neospend.models.masters;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ExpenseItem extends BaseEntity {

	private String expenseName;

	private Long unitPrice;

	private Long quantity;

	private Long Amount;
	
	
	//@ManyToOne
	//private ExpenseRequest expenseRequest;

}
