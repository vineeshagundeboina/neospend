package com.neomaxer.neospend.models.masters;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.beans.factory.annotation.Autowired;

import com.neomaxer.neospend.repositories.masters.ExpenseHistoryRepo;

public class ExpenseRequestEntityListener {
  
	
	@Autowired
	private ExpenseHistoryRepo expenseHistoryRepo;
	
    @PrePersist
    public void prePersist(ExpenseRequest expenseRequest) {
        createExpenseHistory(expenseRequest);
    }

    @PreUpdate
    public void preUpdate(ExpenseRequest expenseRequest) {
        // Handle updates if needed
    }

    private void createExpenseHistory(ExpenseRequest expenseRequest) {
    	ExpenseHistory expenseHistory = new ExpenseHistory();
	    expenseHistory.setRequestStatus(expenseRequest.getRequestStatus());
	    expenseHistory.setRequestAmount(expenseRequest.getRequestAmount());
	    expenseHistory.setRequestNotes(expenseRequest.getRequestNotes());
	    expenseHistory.setMakerRole(expenseRequest.getWorkflow().getMakerRole());
	    expenseHistory.setCheckerRole(expenseRequest.getWorkflow().getCheckerRole());
	  //  expenseHistory.setExpenseRequest(expenseRequest);
//	    expenseHistory.setReimbursmentRequest(reimbursmentRequest);
//	    expenseHistory.setSanctionRequest(sanctionRequest);
	    System.out.println(expenseRequest.getRequestStatus());
	    
	    System.out.println(expenseRequest.getWorkflow().getMakerRole());
	    System.out.println(expenseRequest);
	    System.out.println(expenseHistory);
	   // expenseHistoryRepo.save(expenseHistory);
	  //  expenseHistoryRepo.saveAndFlush(expenseHistory);

    }


}
