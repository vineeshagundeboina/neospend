package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.ExpenseCategory;
import com.neomaxer.neospend.repositories.masters.ExpenseCategoryRepo;

@RestController
@RequestMapping("/expenseCategory")
public class ExpenseCategoryController extends GenericController<ExpenseCategory> {

	public ExpenseCategoryController(ExpenseCategoryRepo repository) {
		super(repository);

	}
}
