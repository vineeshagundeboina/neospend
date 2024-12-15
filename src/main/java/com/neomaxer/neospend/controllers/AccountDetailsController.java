package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.masters.AccountDetails;
import com.neomaxer.neospend.repositories.masters.AccountDetailsRepo;

@RestController
@RequestMapping("/accountDetails")
public class AccountDetailsController extends GenericController<AccountDetails> {

	public AccountDetailsController(AccountDetailsRepo repository) {
		super(repository);
	}

}
