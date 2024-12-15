package com.neomaxer.neospend.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.common.GenericController;
import com.neomaxer.neospend.models.auth.Profile;
import com.neomaxer.neospend.repositories.masters.ProfileRepo;

@RestController
@RequestMapping("/api/profile")
public class ProfileController extends GenericController<Profile> {

	public ProfileController(ProfileRepo repository) {
		super(repository);
	}

}
