package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.SanctionRequestWorkflow;

public class SanctionRequestWorkflowService extends GenericService<SanctionRequestWorkflow> {

	public SanctionRequestWorkflowService(GenericRepository<SanctionRequestWorkflow> repository) {
		super(repository);
	}
}
