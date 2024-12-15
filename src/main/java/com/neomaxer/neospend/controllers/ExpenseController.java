package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.Expense;
import com.neomaxer.neospend.repositories.masters.ExpenseRepo;

@RestController
@RequestMapping("/expense")
public class ExpenseController extends GenericController<Expense> {

	public ExpenseController(ExpenseRepo repository) {
		super(repository);
		
	}

}
