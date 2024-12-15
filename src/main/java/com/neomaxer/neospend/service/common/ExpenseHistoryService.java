package com.neomaxer.neospend.service.common;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.persistence.PostPersist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neomaxer.neospend.models.masters.ExpenseHistory;
import com.neomaxer.neospend.models.masters.ExpenseRequest;
import com.neomaxer.neospend.models.masters.SanctionRequest;
import com.neomaxer.neospend.repositories.masters.ExpenseHistoryRepo;

@Service
public class ExpenseHistoryService {

	@Autowired
	private ExpenseHistoryRepo expenseHistoryRepo;

	public List<ExpenseHistory> getAllRequests() {
		return expenseHistoryRepo.findAll();
	}

	public Optional<ExpenseHistory> getAllRequestsById(UUID requestId) {
		return expenseHistoryRepo.findById(requestId);
	}

	@PostPersist
	public void afterExpenseRequestCreated(ExpenseRequest expenseRequest) {
		// Create a new ExpenseHistory record and populate it with data from the
		// ExpenseRequest
		ExpenseHistory expenseHistory = new ExpenseHistory();
		expenseHistory.setRequestStatus(expenseRequest.getRequestStatus());

		expenseHistory.setRequestAmount(expenseRequest.getRequestAmount());

		expenseHistory.setRequestNotes(expenseRequest.getRequestNotes());
		expenseHistory.setMakerRole(expenseRequest.getWorkflow().getMakerRole());
		expenseHistory.setCheckerRole(expenseRequest.getWorkflow().getCheckerRole());
		expenseHistory.setExpenseRequest(expenseRequest);

//	    expenseHistory.setReimbursmentRequest(reimbursmentRequest);
//	    expenseHistory.setSanctionRequest(sanctionRequest);
		expenseHistoryRepo.save(expenseHistory);
	}
	
	@PostPersist
	public void afterSanctionRequestCreated(SanctionRequest sanctionRequest) {
		// Create a new ExpenseHistory record and populate it with data from the
		// ExpenseRequest
		ExpenseHistory expenseHistory = new ExpenseHistory();
		expenseHistory.setRequestStatus(sanctionRequest.getRequestStatus());

		expenseHistory.setRequestAmount(sanctionRequest.getSanctionAmount());

		expenseHistory.setRequestNotes(sanctionRequest.getNotes());
		expenseHistory.setMakerRole(sanctionRequest.getWorkflow().getMakerRole());
		expenseHistory.setCheckerRole(sanctionRequest.getWorkflow().getCheckerRole());
		expenseHistory.setSanctionRequest(sanctionRequest);

//	    expenseHistory.setReimbursmentRequest(reimbursmentRequest);
//	    expenseHistory.setSanctionRequest(sanctionRequest);
		expenseHistoryRepo.save(expenseHistory);
	}
}
