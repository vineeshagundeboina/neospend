package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.Vendor;
import com.neomaxer.neospend.repositories.masters.VendorRepo;
@RestController
@RequestMapping("/vendor")
public class VendorController extends GenericController<Vendor> {

	public VendorController(VendorRepo repository) {
		super(repository);

	}
}
