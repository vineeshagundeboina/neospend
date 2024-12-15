package com.neomaxer.neospend.service.common;

import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.InvoiceDetails;

@Service
public class BillDetailsService extends GenericService<InvoiceDetails> {

	public BillDetailsService(GenericRepository<InvoiceDetails> repository) {
		super(repository);
	}

}
