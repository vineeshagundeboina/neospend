package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.TaxRate;
import com.neomaxer.neospend.repositories.masters.TaxRateRepo;

@RestController
@RequestMapping("/taxrate")
public class TaxRateController extends GenericController<TaxRate> {

	public TaxRateController(TaxRateRepo repository) {
		super(repository);

	}
}
