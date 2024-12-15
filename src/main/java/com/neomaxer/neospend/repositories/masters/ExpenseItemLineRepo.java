package com.neomaxer.neospend.repositories.masters;

import java.util.List;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.ExpenseItemLine;
import com.neomaxer.neospend.models.masters.ExpenseRequest;

public interface ExpenseItemLineRepo extends GenericRepository<ExpenseItemLine> {

	List<ExpenseItemLine> findByExpenseRequest(ExpenseRequest expenseRequest);

}
