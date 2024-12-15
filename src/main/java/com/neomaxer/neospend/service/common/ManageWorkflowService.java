package com.neomaxer.neospend.service.common;

import org.springframework.beans.factory.annotation.Autowired;

import com.neomaxer.neospend.common.GenericRepository;
import com.neomaxer.neospend.common.GenericService;
import com.neomaxer.neospend.models.masters.CompanyType;
import com.neomaxer.neospend.models.masters.Workflow;

public class ManageWorkflowService extends GenericService<Workflow> {

	@Autowired
	CompanyType companyType;

	Workflow manageWorkflow = new Workflow();
	{

		// Here we are setting that if companytype is soleproprietorship then only one
		// i.e Maker role is there,no parent role
		if (companyType.equals(CompanyType.Soleproprietorship)) {
			//manageWorkflow.setCheckerRole(null);

			//System.out.println("only one  Authorizer is allowed:" + manageWorkflow.getMakerRole());
		}

		else {

			//System.out.println(
					//"Many  Authorizer is allowed:" + manageWorkflow.getMakerRole() + manageWorkflow.getCheckerRole());

		}

	}

	public ManageWorkflowService(GenericRepository<Workflow> repository) {
		super(repository);

	}

	/*
	 * Get the global settings Loop and find CoolingPeriod key =>
	 * 
	 * 
	 * 
	 */

}
