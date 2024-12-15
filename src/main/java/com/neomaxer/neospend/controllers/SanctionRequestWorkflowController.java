package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.SanctionRequestWorkflow;
import com.neomaxer.neospend.repositories.masters.SanctionRequestWorkflowRepo;


@RestController
@RequestMapping("/sanctionRequestWorkflow")
public class SanctionRequestWorkflowController extends GenericController<SanctionRequestWorkflow> {

	public SanctionRequestWorkflowController(SanctionRequestWorkflowRepo repository) {
		super(repository);
	}
}
