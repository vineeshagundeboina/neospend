package com.neomaxer.neospend.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.models.masters.SanctionItemLine;
import com.neomaxer.neospend.models.masters.SanctionRequest;
import com.neomaxer.neospend.repositories.masters.SanctionItemLineRepo;

@RestController
@RequestMapping("/sanctionitemline")
public class SanctionItemLineController extends GenericController<SanctionItemLine> {

	public SanctionItemLineController(GenericRepository<SanctionItemLine> repository) {
		super(repository);
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	private SanctionItemLineRepo sanctionItemLineRepo;
	
	@GetMapping("/bySanctionRequest/{requestId}")
	public List<SanctionItemLine> getSanctionItemLineBySanctionRequest(@PathVariable UUID requestId) {
		SanctionRequest sanctionRequest = new SanctionRequest();
		sanctionRequest.setId(requestId);
		return sanctionItemLineRepo.findBySanctionRequest(sanctionRequest);
	}

	
	

}
