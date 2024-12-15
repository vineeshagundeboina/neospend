package com.neomaxer.neospend.service.common;

import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.PaymentRequestWorkflow;

@Service
public class PaymentRequestWorkflowService extends GenericService<PaymentRequestWorkflow> {

	public PaymentRequestWorkflowService(GenericRepository<PaymentRequestWorkflow> repository) {
		super(repository);
	}

}
