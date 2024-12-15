package com.neomaxer.neospend.service.common;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.TaxRate;

public class TaxRateService extends GenericService<TaxRate> {

	public TaxRateService(GenericRepository<TaxRate> repository) {
		super(repository);
}
}
