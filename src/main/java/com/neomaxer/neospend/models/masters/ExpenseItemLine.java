package com.neomaxer.neospend.models.masters;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import com.neomaxer.neospend.models.common.BaseEntity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class ExpenseItemLine extends BaseEntity {
   @ManyToOne
	private Expense expenseName;

	private BigDecimal unitPrice;

	private Long quantity;

	private BigDecimal Amount;
	
	
	private BigDecimal discount;
	
	@ManyToOne
	private ExpenseRequest expenseRequest;

	@PrePersist
	@PreUpdate
	public void calculateAmount() {
		if (unitPrice != null && quantity != null) {
			BigDecimal totalAmount = unitPrice.multiply(new BigDecimal(quantity));

			if (discount != null) {
				totalAmount = totalAmount.subtract(discount);
			}
			this.setAmount(totalAmount);
		}
	}

}
