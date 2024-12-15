package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.PaymentRequestWorkflow;
import com.neomaxer.neospend.repositories.masters.PaymentRequestWorkflowRepo;

@RestController
@RequestMapping("/paymentRequestWorkflow")

public class PaymentRequestWorkflowController extends GenericController<PaymentRequestWorkflow> {

	public PaymentRequestWorkflowController(PaymentRequestWorkflowRepo repository) {
		super(repository);

	}

}