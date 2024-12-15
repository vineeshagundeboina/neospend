package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.ApplicationDetails;
import com.neomaxer.neospend.repositories.masters.ApplicationDetailsRepo;

@RestController
@RequestMapping("/applicationDetails")

public class ApplicationDetailsController extends GenericController<ApplicationDetails> {

	public ApplicationDetailsController(ApplicationDetailsRepo repository) {
		super(repository);
	}
}
