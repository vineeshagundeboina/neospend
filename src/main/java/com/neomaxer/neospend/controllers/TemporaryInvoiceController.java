package com.neomaxer.neospend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.InvoiceSequence;
import com.neomaxer.neospend.models.masters.TemporaryInvoice;
import com.neomaxer.neospend.repositories.masters.InvoiceSequenceRepo;
import com.neomaxer.neospend.repositories.masters.TemporaryInvoiceRepo;

@RestController
@RequestMapping("/temporaryInvoice")
public class TemporaryInvoiceController extends GenericController<TemporaryInvoice> {

	public TemporaryInvoiceController(TemporaryInvoiceRepo repository) {
		super(repository);
	}

	@Autowired
	private TemporaryInvoiceRepo temporaryInvoiceRepo;

	@Autowired
	private InvoiceSequenceRepo sequenceRepo;

	@PostMapping("/create-temporary-invoice")
	public ResponseEntity<String> createTemporaryInvoice(@RequestBody TemporaryInvoice temporaryInvoice) {

//		ExpenseRequest expenseRequest = expenseRequestRepo.findByRequestStatus("APPROVED");
//
//		if (expenseRequest == null) {
//			return ResponseEntity.badRequest().body("No approved ExpenseRequest found");
//		}

		// temporaryInvoiceRepo.save(temporaryInvoice);

		InvoiceSequence sequence = sequenceRepo.findByPrefix("INV-23-24-");
		if (sequence == null) {
			sequence = new InvoiceSequence();
			sequence.setPrefix("INV-23-24-");
			sequence.setSequenceNumber(1L);
		} else {
			sequence.setSequenceNumber(sequence.getSequenceNumber() + 1);
		}

		String invoiceNumber = sequence.getPrefix() + String.format("%03d", sequence.getSequenceNumber());
		temporaryInvoice.setInvoiceNo(invoiceNumber);

		// Save the temporary invoice and update the sequence
		temporaryInvoiceRepo.save(temporaryInvoice);
		sequenceRepo.save(sequence);
		// return ResponseEntity.ok(temporaryInvoice)

		return ResponseEntity
				.ok("Temporary Invoice created successfully with invoice number:" + temporaryInvoice.getInvoiceNo());

	}
}