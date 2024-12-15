package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.ReimbursmentRequestWorkflow;
import com.neomaxer.neospend.repositories.masters.ReimbursmentRequestWorkflowRepo;

@RestController
@RequestMapping("/reimbursmentRequestWorkflow")
public class ReimbursmentRequestWorkflowController extends GenericController<ReimbursmentRequestWorkflow> {

	public ReimbursmentRequestWorkflowController(ReimbursmentRequestWorkflowRepo repository) {
		super(repository);

	}

}
