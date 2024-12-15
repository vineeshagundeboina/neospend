package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.WorkflowItem;

public class WorkflowListService extends GenericService<WorkflowItem> {

	public WorkflowListService(GenericRepository<WorkflowItem> repository) {
		super(repository);

	}

}
