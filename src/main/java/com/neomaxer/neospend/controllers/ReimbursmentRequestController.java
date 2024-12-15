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
import com.neomaxer.neospend.models.masters.ReimbursmentRequest;
import com.neomaxer.neospend.repositories.masters.ReimbursmentRequestRepo;
import com.neomaxer.neospend.service.common.ReimbursmentRequestService;



@RestController
@RequestMapping("/reimbursmentRequest")
public class ReimbursmentRequestController extends GenericController<ReimbursmentRequest> {

	public ReimbursmentRequestController(ReimbursmentRequestRepo repository) {
		super(repository);
	}

	@Autowired
	ReimbursmentRequestService reimbursmentRequestService;

	@PostMapping("/requestReimbursment")
	public ResponseEntity<ReimbursmentRequest> create(@RequestBody ReimbursmentRequest reimbursmentRequest) {
		ReimbursmentRequest response = reimbursmentRequestService.createReimbursmentRequest(reimbursmentRequest);
		reimbursmentRequestService.saveReimbursmentRequest(response);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/requestPayment/{reimbursmentRequestId}/uploadAttachment")
	public ResponseEntity<ReimbursmentRequest> uploadAttachment(@PathVariable UUID reimbursmentRequestId,
			@RequestParam("file") MultipartFile[] files) {
		ReimbursmentRequest response = reimbursmentRequestService.uploadAttachment(reimbursmentRequestId, files);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/rejectReimbursment")
	public ResponseEntity<ReimbursmentRequest> createReject(@RequestBody ReimbursmentRequest requestReimbursment) {
		ReimbursmentRequest response = reimbursmentRequestService.createRejectReimbursmentRequest(requestReimbursment);
		return ResponseEntity.ok(response);
	}
}