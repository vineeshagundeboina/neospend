package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.ExpenseInvoice;
import com.neomaxer.neospend.repositories.masters.ExpenseInvoiceRepo;

@RestController
@RequestMapping("/expenseInvoice")
public class ExpenseInvoiceController extends GenericController<ExpenseInvoice> {

	public ExpenseInvoiceController(ExpenseInvoiceRepo repository) {
		super(repository);

}
}
