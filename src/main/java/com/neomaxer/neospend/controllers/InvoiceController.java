package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.InvoiceDetails;
import com.neomaxer.neospend.repositories.masters.InvoiceDetailsRepo;

@RestController
@RequestMapping("/invoiceDetails")

public class InvoiceController extends GenericController<InvoiceDetails> {

	public InvoiceController(InvoiceDetailsRepo repository) {
		super(repository);

	}

}
