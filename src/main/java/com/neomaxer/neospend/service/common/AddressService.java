package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.Address;

public class AddressService extends GenericService<Address> {

	public AddressService(GenericRepository<Address> repository) {
		super(repository);
	}
}
