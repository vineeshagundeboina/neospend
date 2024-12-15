package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.ApplicationDetails;

public class ApplicationDetailsService extends GenericService<ApplicationDetails> {

	public ApplicationDetailsService(GenericRepository<ApplicationDetails> repository) {
		super(repository);

	}
}
