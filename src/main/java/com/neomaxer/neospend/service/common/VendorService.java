package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.Vendor;

public class VendorService extends GenericService<Vendor> {

	public VendorService(GenericRepository<Vendor> repository) {

		super(repository);
	}

}
