package com.neomaxer.neospend.service.common;

import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.ExpenseItemLine;

@Service
public class ExpenseItemLineService extends GenericService<ExpenseItemLine> {

	public ExpenseItemLineService(GenericRepository<ExpenseItemLine> repository) {
		super(repository);

	}
//
//	@Autowired
//	ExpenseItemLineRepo expenseItemRepo;
//
//	public ExpenseItem createExpenseItem(ExpenseItemDTO expenseItemDTO) {
//		ExpenseItem expenseItem = new ExpenseItem();
//		expenseItem.setExpenseName(expenseItemDTO.getExpenseName());
//		expenseItem.setUnitPrice(expenseItemDTO.getUnitPrice());
//		expenseItem.setQuantity(expenseItemDTO.getQuantity());
//
//		if (expenseItem.getUnitPrice() != null && expenseItem.getQuantity() != null) {
//			expenseItem.setAmount(expenseItem.getUnitPrice() * expenseItem.getQuantity());
//		}
//
//		return expenseItemRepo.save(expenseItem);
//	}
}
