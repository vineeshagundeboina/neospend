package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.ExpenseCategory;

public class ExpenseCategoryService extends GenericService<ExpenseCategory> {

	public ExpenseCategoryService(GenericRepository<ExpenseCategory> repository) {
		super(repository);

	}
}
