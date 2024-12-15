package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.OneTimeVendor;
import com.neomaxer.neospend.repositories.masters.OneTimeVendorRepo;
@RestController
@RequestMapping("/oneTimeVendor")
public class OneTimeVendorController extends GenericController<OneTimeVendor> {

	public OneTimeVendorController(OneTimeVendorRepo repository) {
		super(repository);

	}
}
