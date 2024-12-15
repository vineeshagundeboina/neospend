package com.neomaxer.neospend.service.common;

import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.ReimbursmentRequestWorkflow;

@Service
public class ReimbursmentRequestWorkflowService extends GenericService<ReimbursmentRequestWorkflow> {

	public ReimbursmentRequestWorkflowService(GenericRepository<ReimbursmentRequestWorkflow> repository) {
		super(repository);
	}
}
