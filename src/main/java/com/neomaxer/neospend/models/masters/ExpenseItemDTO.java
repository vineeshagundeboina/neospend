package com.neomaxer.neospend.models.masters;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ExpenseItemDTO {
	
	private String expenseName;
    private Long unitPrice;
    private Long quantity;

}
