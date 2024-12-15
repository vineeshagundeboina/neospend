package com.neomaxer.neospend.controllers;

import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.DelegationRole;
import com.neomaxer.neospend.models.masters.ExpenseRequest;
import com.neomaxer.neospend.models.masters.RequestStatus;
import com.neomaxer.neospend.repositories.masters.DelegationRoleRepo;
import com.neomaxer.neospend.repositories.masters.ExpenseHistoryRepo;
import com.neomaxer.neospend.repositories.masters.ExpenseRequestRepo;
import com.neomaxer.neospend.repositories.masters.SanctionRequestRepo;
import com.neomaxer.neospend.repositories.masters.WorkflowRepo;
import com.neomaxer.neospend.service.common.ExpenseHistoryService;
import com.neomaxer.neospend.service.common.ExpenseRequestService;

@RestController
@RequestMapping("/expenseRequest")
public class ExpenseRequestController extends GenericController<ExpenseRequest> {

	public ExpenseRequestController(ExpenseRequestRepo repository) {
		super(repository);
	}

	@Autowired
	SanctionRequestRepo sanctionRequestRepo;
	
	@Autowired
	private ExpenseHistoryService expenseHistoryService;

	@Autowired
	ExpenseRequestRepo expenseRequestRepo;

	@Autowired
	ExpenseRequestService expenseRequestService;

	@Autowired
	DelegationRoleRepo delegationRoleRepo;

	ExpenseRequest expenseRequest;

//	@Autowired
//	private FileUploadRepo fileUploadRepo;

	@Autowired
	WorkflowRepo workflowRepo;

	@Autowired
	ExpenseHistoryRepo expenseHistoryRepo;

	DelegationRole delegationRole;

	@PutMapping("/{expenseRequestId}/update-status")
	public ResponseEntity<ExpenseRequest> updateRequestStatus(@PathVariable UUID expenseRequestId,
			@RequestParam RequestStatus newStatus) {
		ExpenseRequest updatedRequest = expenseRequestService.updateRequestStatus(expenseRequestId, newStatus);
		return ResponseEntity.ok(updatedRequest);
	}

	@PutMapping("/{expenseRequestId}/handle-advance")
	public ResponseEntity<String> handleAdvance(@PathVariable UUID expenseRequestId,
			@RequestParam RequestStatus newStatus, @RequestParam BigDecimal returnedAmount) {
		ExpenseRequest updatedRequest = expenseRequestService.updateRequestStatus(expenseRequestId, newStatus);

		if (newStatus == RequestStatus.APPROVED) {
			if (returnedAmount.compareTo(updatedRequest.getAdvanceAmount()) > 0) {
				return ResponseEntity.badRequest().body("Returned amount cannot be greater than advance amount");
			}

			BigDecimal remainingAdvance = updatedRequest.getAdvanceAmount().subtract(returnedAmount);

			if (remainingAdvance.compareTo(BigDecimal.ZERO) > 0) {

				ExpenseRequest newExpenseRequest = new ExpenseRequest();

				newExpenseRequest.setProfile(updatedRequest.getProfile());
				newExpenseRequest.setAdvanceAmount(remainingAdvance);

				expenseRequestRepo.save(newExpenseRequest);
			}
		}

		return ResponseEntity.ok("Expense request updated successfully.");
	}
	
	@PostMapping("/createExpenserequest")
	public ResponseEntity<?> savingExpenseRequestIntoExpenseHistoryTable(@RequestBody ExpenseRequest expenseRequest){
		
		//System.out.println("maker rool-->"+expenseRequest.getWorkflow().getMakerRole());
		expenseRequest=expenseRequestRepo.save(expenseRequest);
		expenseHistoryService.afterExpenseRequestCreated(expenseRequest);
		
		return ResponseEntity.ok("saved");
	}

}

//	@PostMapping("/requestPayment")
//	public ResponseEntity<ExpenseRequest> create(@RequestBody ExpenseRequest expenseRequest) {
//		ExpenseRequest response = expenseRequestService.createExpenseRequest(expenseRequest);
//		return ResponseEntity.ok(response);
//	}
//	
//	@PostMapping("/{expenseRequestId}/addExpenseItems")
//	public ResponseEntity<ExpenseRequest> addExpenseItems(
//	        @PathVariable UUID expenseRequestId,
//	        @RequestBody List<ExpenseItem> expenseItems) {
//	    
//	    ExpenseRequest expenseRequest = expenseRequestRepo.findById(expenseRequestId)
//	            .orElseThrow(() -> new GenericException("ExpenseRequest not found"));
//
//	    if (expenseRequest.getRequestStatus() != null && expenseRequest.getRequestStatus().equals("INIT")) {
//	        
//	        expenseRequest.setExpenseItemLine(expenseItems);
//	        
//	   
//	        expenseRequestService.updateTotalAmount(expenseRequest);
//
//	        return ResponseEntity.ok(expenseRequestRepo.save(expenseRequest));
//	    } else {
//	        throw new GenericException("Cannot add Expense Items to this request.");
//	    }
//	}
//
//	@PostMapping("/requestPayment/{expenseRequestId}/uploadAttachment")
//	public ResponseEntity<ExpenseRequest> uploadAttachment(@PathVariable UUID expenseRequestId,
//			@RequestParam("file") MultipartFile[] files) {
//		ExpenseRequest response = expenseRequestService.uploadAttachment(expenseRequestId, files);
//		return ResponseEntity.ok(response);
//	}

//	@PostMapping("/rejectPayment")
//	public ResponseEntity<ExpenseRequest> createReject(@RequestBody ExpenseRequest expenseRequest) {
//		ExpenseRequest response = expenseRequestService.createRejectExpenseRequest(expenseRequest);
//		return ResponseEntity.ok(response);
//	}
//}