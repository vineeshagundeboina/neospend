package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.auth.Role;

public class RoleService extends GenericService<Role> {
	public RoleService(GenericRepository<Role> repository) {
		super(repository);

	}
}
