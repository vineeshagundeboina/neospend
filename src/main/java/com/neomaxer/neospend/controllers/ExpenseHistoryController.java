package com.neomaxer.neospend.controllers;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.models.masters.ExpenseHistory;
import com.neomaxer.neospend.models.masters.ExpenseRequest;
import com.neomaxer.neospend.repositories.masters.ExpenseHistoryRepo;
import com.neomaxer.neospend.repositories.masters.ExpenseRequestRepo;
import com.neomaxer.neospend.service.common.ExpenseHistoryService;

@RestController
@RequestMapping("/expenseHistory")
public class ExpenseHistoryController {

	@Autowired
	private ExpenseHistoryService expenseHistoryService;

	@Autowired
	private ExpenseRequestRepo expenseRequestRepo;

	@Autowired
	private ExpenseHistoryRepo expenseHistoryRepo;

	@GetMapping("/requests")
	public List<ExpenseHistory> getAllRequests() {
		return expenseHistoryService.getAllRequests();
	}

	@GetMapping("/requests/{requestId}")
	public Optional<ExpenseHistory> getAllRequestsById(@PathVariable UUID requestId) {
		return expenseHistoryService.getAllRequestsById(requestId);
	}

	/*@PostMapping("/saving approvedexpenserequest into expensehistory/{id}")

	public ResponseEntity<?> savingExpenseRequestIntoExpenseHistory(@PathVariable("id") UUID id) {

		Optional<ExpenseRequest> expenseRequestOptional = expenseRequestRepo.findById(id);

		if (expenseRequestOptional.isPresent()) {

			ExpenseRequest expenseRequest = expenseRequestOptional.get();

			if (expenseRequest.getRequestStatus().toString().equals("APPROVED")) {

				ExpenseHistory expenseHistory = new ExpenseHistory();

				expenseHistory.setRequestStatus(expenseRequest.getRequestStatus());

				expenseHistory.setRequestNotes(expenseRequest.getRequestNotes());

				expenseHistory.setRequestAmount(expenseRequest.getRequestAmount());

				expenseHistory.setMakerRole(expenseRequest.getWorkflow().getMakerRole());

				expenseHistory.setCheckerRole(expenseRequest.getWorkflow().getCheckerRole());

				expenseHistory.setExpenseRequest(expenseRequest);

				expenseHistoryRepo.save(expenseHistory);

				return ResponseEntity.ok("Expense request saved in history.");

			}

		}

		return ResponseEntity.badRequest().body("Expense request not found or not approved.");

	}*/

}
