package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.auth.Role;

@RestController
@RequestMapping("/api/roles")
public class RoleController extends GenericController<Role> {

	public RoleController(GenericRepository<Role> repository) {
		super(repository);
	}
}
