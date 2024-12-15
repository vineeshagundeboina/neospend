package com.neomaxer.neospend.service.common;

import org.springframework.stereotype.Service;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.SanctionItemLine;

@Service
public class SanctionItemLineService extends GenericService<SanctionItemLine>{

	public SanctionItemLineService(GenericRepository<SanctionItemLine> repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}
	

}
