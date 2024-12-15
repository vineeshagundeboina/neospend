package com.neomaxer.neospend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.ExpenseItem;
import com.neomaxer.neospend.models.masters.ExpenseItemDTO;
import com.neomaxer.neospend.repositories.masters.ExpenseItemRepo;
import com.neomaxer.neospend.service.common.ExpenseItemService;

@RestController
@RequestMapping("/expenseItem")
public class ExpenseItemController extends GenericController<ExpenseItem> {

	public ExpenseItemController(ExpenseItemRepo repository) {
		super(repository);

	}
	@Autowired
	private ExpenseItemService expenseItemService;

	@PostMapping("/lineItem")
	public ResponseEntity<ExpenseItem> createNewExpenseItem(@RequestBody ExpenseItemDTO expenseItemDTO) {
		ExpenseItem createdExpenseItem = expenseItemService.createExpenseItem(expenseItemDTO);
		return new ResponseEntity<>(createdExpenseItem, HttpStatus.CREATED);
	}
}
