package com.neomaxer.neospend.service.common;

import java.util.UUID;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.ExpenseInvoice;
import com.neomaxer.neospend.models.masters.ExpenseRequest;
import com.neomaxer.neospend.models.masters.RequestStatus;
import com.neomaxer.neospend.repositories.masters.ExpenseRequestRepo;

@Service
public class ExpenseInvoiceService extends GenericService<ExpenseInvoice> {

	public ExpenseInvoiceService(GenericRepository<ExpenseInvoice> repository) {
		super(repository);

	}

	@Autowired
	private ExpenseRequestRepo expenseRequestRepo;

//	@Autowired
//	private ExpenseInvoiceRepo expenseInvoiceRepo;

	public void createExpenseInvoiceIfApproved(UUID expenseRequestId) {
		ExpenseRequest expenseRequest = expenseRequestRepo.findById(expenseRequestId)
				.orElseThrow(() -> new EntityNotFoundException("ExpenseRequest not found"));

		if (expenseRequest.getRequestStatus() == RequestStatus.APPROVED) {
			
			
			// expenseInvoice.setExpenseRequest(expenseRequest);
			// expenseInvoice.setId(expenseRequestId);

			// expenseInvoiceRepo.save(expenseInvoice);
		}
	}
}
