package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.WorkflowItem;
import com.neomaxer.neospend.repositories.masters.WorkflowItemRepo;

@RestController
@RequestMapping("/workflowItem")

public class WorkflowItemController extends GenericController<WorkflowItem> {

	public WorkflowItemController(WorkflowItemRepo repository) {
		super(repository);

	}

}
