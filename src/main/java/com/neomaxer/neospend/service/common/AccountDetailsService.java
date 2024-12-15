package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.AccountDetails;

public class AccountDetailsService extends GenericService<AccountDetails> {

	public AccountDetailsService(GenericRepository<AccountDetails> repository) {
		super(repository);

	}

}
