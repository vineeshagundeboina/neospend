package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.OneTimeVendor;

public class OneTimeVendorService extends GenericService<OneTimeVendor> {

	public OneTimeVendorService(GenericRepository<OneTimeVendor> repository) {

		super(repository);
	}

}
