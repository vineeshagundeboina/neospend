package com.neomaxer.neospend.controllers;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.SanctionRequest;
import com.neomaxer.neospend.repositories.masters.SanctionRequestRepo;
import com.neomaxer.neospend.service.common.ExpenseHistoryService;
import com.neomaxer.neospend.service.common.SanctionRequestService;

@RestController
@RequestMapping("/sanctionRequest")
public class SanctionRequestController extends GenericController<SanctionRequest> {

	public SanctionRequestController(SanctionRequestRepo repository) {
		super(repository);
	}

	@Autowired
	SanctionRequestRepo sanctionRequestRepo;

	@Autowired
	SanctionRequestService sanctionRequestService;
	
	@Autowired
	ExpenseHistoryService expenseHistoryService;

	@PostMapping("/requestSanction")
	public ResponseEntity<SanctionRequest> create(@RequestBody SanctionRequest sanctionRequest) {
		sanctionRequest = sanctionRequestService.createSanctionRequest(sanctionRequest);
        expenseHistoryService.afterSanctionRequestCreated(sanctionRequest);
		return ResponseEntity.ok(sanctionRequest);
	}

	@PostMapping("/requestPayment/{sanctionRequestId}/uploadAttachment")
	public ResponseEntity<SanctionRequest> uploadAttachment(@PathVariable UUID sanctionRequestId,
			@RequestParam("file") MultipartFile[] files) {
		SanctionRequest response = sanctionRequestService.uploadAttachment(sanctionRequestId, files);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/rejectSanction")
	public ResponseEntity<SanctionRequest> createReject(@RequestBody SanctionRequest requestSanction) {
		SanctionRequest response = sanctionRequestService.createRejectSanctionRequest(requestSanction);
		return ResponseEntity.ok(response);
	}
}
