package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.Workflow;
import com.neomaxer.neospend.repositories.masters.WorkflowRepo;

@RestController
@RequestMapping("/workflow")

public class WorkflowController extends GenericController<Workflow> {

	public WorkflowController(WorkflowRepo repository) {
		super(repository);

	}

}
