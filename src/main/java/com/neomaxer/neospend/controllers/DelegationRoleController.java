package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.DelegationRole;
import com.neomaxer.neospend.repositories.masters.DelegationRoleRepo;

@RestController
@RequestMapping("/delegationRole")

public class DelegationRoleController extends GenericController<DelegationRole> {

	public DelegationRoleController(DelegationRoleRepo repository) {
		super(repository);

	}
	
	
	
	
}
