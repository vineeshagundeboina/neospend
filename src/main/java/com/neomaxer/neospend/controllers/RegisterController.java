package com.neomaxer.neospend.controllers;

import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.neomaxer.neospend.controllers.auth.SignupRequest;
import com.neomaxer.neospend.exception.GenericException;
import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.models.masters.Company;
import com.neomaxer.neospend.models.masters.Customer;
import com.neomaxer.neospend.models.masters.Partner;
import com.neomaxer.neospend.pojos.response.MessageResponse;
import com.neomaxer.neospend.repositories.auth.UserRepo;
import com.neomaxer.neospend.repositories.masters.CompanyRepo;
import com.neomaxer.neospend.repositories.masters.PartnersRepo;
import com.neomaxer.neospend.service.common.RegisterService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/register")
@Tag(name = "User Registration", description = "APIs for UserRegistration related operations")
public class RegisterController {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	CompanyRepo companyRepo;

	@Autowired
	private PartnersRepo partnerRepo;

	@Autowired
	private RegisterService registerService;

	@Transactional
	@PostMapping("/signup")
	public ResponseEntity<?> signupWithEmail(@RequestBody SignupRequest signupRequest) {

		// TODO validate if mobile number already exists and reject signup ask to sign
		// in instead
		Optional<User> user = userRepo.findByUserName(signupRequest.getMobileNumber());
		Optional<Company> existingCompany = companyRepo.findByCompanyPanNumber(signupRequest.getPanNumber());

		if (user.isPresent()) {
			throw new GenericException("Mobile Number already exists, sign in instead");
		}

		if (existingCompany.isPresent()) {
			throw new GenericException("PAN number already exists, sign in instead");
		}

		if (!signupRequest.getPartnerCode().equals("")) {
			Optional<Partner> existingPartner = partnerRepo.findByPartnerCode(signupRequest.getPartnerCode());

			if (!existingPartner.isPresent()) {
				throw new GenericException("Please check the partner code and try again.");
			}
		}
		boolean status = registerService.signUp(signupRequest);

		if (!status) {
			throw new GenericException("Could not complete sign up");
		}

		return ResponseEntity.ok(new MessageResponse("Signup Success, verify email"));
	}

	@GetMapping("/verifyemail")
	public ResponseEntity<?> verifyemail(@RequestParam("token") String token) {
		Optional<User> user = userRepo.findByActivationToken(token);
		if (user.isEmpty()) {
			throw new GenericException("Invalid Token");
		} else {
			User _user = user.get();
			if (_user.getIsActive()) {
				return ResponseEntity.ok(new MessageResponse("User is already active"));
			}
			Date dt = _user.getEmailTokenExpiryDate();
			if (dt.before(new Date())) {
				throw new GenericException("Token Expired");
			}
			_user.setIsActive(true);
			_user.setEmailVerified(true);
			userRepo.save(_user);
		}

		return ResponseEntity.ok(new MessageResponse("Email Verification Success"));
	}

	@Transactional
	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody SignupRequest signupRequest) {
		Customer customer = registerService.createCustomer(signupRequest);
		if (customer == null || customer.equals("null")) {
			throw new GenericException("Could not complete sign up");
		}

		// TODO: send otp to the customer mobile number

		return ResponseEntity.ok(customer);
	}

}
