package com.neomaxer.neospend.service.common;

import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.DelegationRole;

@Service
public class DelegationRoleService extends GenericService<DelegationRole> {

	public DelegationRoleService(GenericRepository<DelegationRole> repository) {
		super(repository);
	}

}
