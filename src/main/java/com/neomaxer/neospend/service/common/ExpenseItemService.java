package com.neomaxer.neospend.service.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.ExpenseItem;
import com.neomaxer.neospend.models.masters.ExpenseItemDTO;
import com.neomaxer.neospend.repositories.masters.ExpenseItemRepo;

@Service
public class ExpenseItemService extends GenericService<ExpenseItem> {

	public ExpenseItemService(GenericRepository<ExpenseItem> repository) {
		super(repository);

	}

	@Autowired
	ExpenseItemRepo expenseItemRepo;

	public ExpenseItem createExpenseItem(ExpenseItemDTO expenseItemDTO) {
		ExpenseItem expenseItem = new ExpenseItem();
		expenseItem.setExpenseName(expenseItemDTO.getExpenseName());
		expenseItem.setUnitPrice(expenseItemDTO.getUnitPrice());
		expenseItem.setQuantity(expenseItemDTO.getQuantity());

		if (expenseItem.getUnitPrice() != null && expenseItem.getQuantity() != null) {
			expenseItem.setAmount(expenseItem.getUnitPrice() * expenseItem.getQuantity());
		}

		return expenseItemRepo.save(expenseItem);
	}
}
