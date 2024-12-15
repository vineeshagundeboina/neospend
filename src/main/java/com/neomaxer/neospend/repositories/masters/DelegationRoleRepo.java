package com.neomaxer.neospend.repositories.masters;

import java.util.Optional;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.DelegationRole;

public interface DelegationRoleRepo extends GenericRepository<DelegationRole> {

	// Optional<DelegationRole> findById(long l);

	Optional<DelegationRole> findByDelegationName(String string);



}