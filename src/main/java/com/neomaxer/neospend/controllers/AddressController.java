package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.Address;
import com.neomaxer.neospend.repositories.masters.AddressRepo;

@RestController
@RequestMapping("/address")
public class AddressController extends GenericController<Address> {

	public AddressController(AddressRepo repository) {
		super(repository);

	}
}