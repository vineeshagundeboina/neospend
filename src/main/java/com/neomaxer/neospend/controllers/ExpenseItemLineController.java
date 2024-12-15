package com.neomaxer.neospend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.ExpenseItemLine;
import com.neomaxer.neospend.models.masters.ExpenseRequest;
import com.neomaxer.neospend.repositories.masters.ExpenseItemLineRepo;

@RestController
@RequestMapping("/expenseItem")
public class ExpenseItemLineController extends GenericController<ExpenseItemLine> {

	public ExpenseItemLineController(ExpenseItemLineRepo repository) {
		super(repository);

	}
	@Autowired
	ExpenseItemLineRepo expenseItemLineRepo;

	@GetMapping("/byExpenseRequest/{requestId}")
	public List<ExpenseItemLine> getExpenseItemLineByExpenseRequest(@PathVariable UUID requestId) {
		ExpenseRequest expenseRequest = new ExpenseRequest();
		expenseRequest.setId(requestId);
		return expenseItemLineRepo.findByExpenseRequest(expenseRequest);
	}

//	@PostMapping("/lineItem")
//	public ResponseEntity<ExpenseItemLine> createNewExpenseItem(@RequestBody ExpenseItemDTO expenseItemDTO) {
//		ExpenseItem createdExpenseItem = expenseItemLineService.createExpenseItem(expenseItemDTO);
//		return new ResponseEntity<>(createdExpenseItem, HttpStatus.CREATED);
//	}
}
