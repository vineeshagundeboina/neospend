package com.neomaxer.neospend.service.common;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.neomaxer.neospend.controllers.auth.SignupRequest;
import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.models.masters.Company;
import com.neomaxer.neospend.models.masters.Customer;
import com.neomaxer.neospend.repositories.auth.UserRepo;
import com.neomaxer.neospend.repositories.masters.CompanyRepo;
import com.neomaxer.neospend.repositories.masters.CustomerRepo;

@Service
public class RegisterService {

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	public boolean signUp(SignupRequest signupRequest) {

		try {

			Company company = new Company();
			company.setCompanyName(signupRequest.getCompanyName());
			company.setDealerCode(signupRequest.getPartnerCode());
			company.setCompanyPanNumber(signupRequest.getPanNumber());
			companyRepo.save(company);

			User user = createUser(signupRequest);

			String token = UUID.randomUUID().toString();
			createVerificationTokenForUser(user, token);
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void createVerificationTokenForUser(User user, String token) {
		user.setActivationToken(token);
		Date expDate = DateUtils.addMilliseconds(new Date(), 24 * 60 * 60 * 1000);
		user.setEmailTokenExpiryDate(expDate);
		userRepo.save(user);
	}

	public User createUser(SignupRequest signupRequest) {
		User user = new User();
		user.setIsActive(false);
		user.setUserName(signupRequest.getMobileNumber());
		user.setMobileNumber(signupRequest.getMobileNumber());
		user.setEmail(signupRequest.getEmail());
		String pwd = signupRequest.getPassword();
		pwd = bCryptPasswordEncoder.encode(pwd);
		user.setPassword(pwd);

		userRepo.save(user);
		return user;
	}

	public Customer createCustomer(SignupRequest signupRequest) {
		User existingUser = userRepo.findByMobileNumber(signupRequest.getMobileNumber());
		Customer cust = new Customer();
		if (existingUser != null) {
			Customer customer = customerRepo.findByUser(existingUser);
			if (customer != null) {
				return customer;
			} else {
				cust.setUser(existingUser);
				customerRepo.save(cust);
			}
		} else {
			// user is not found
			User newUser = createCustomerUser(signupRequest);
			cust.setUser(newUser);
			customerRepo.save(cust);
		}
		return cust;
	}

	public User createCustomerUser(SignupRequest signupRequest) {
		User user = new User();
		user.setIsActive(true);
		user.setUserName(signupRequest.getMobileNumber());
		user.setMobileNumber(signupRequest.getMobileNumber());

		userRepo.save(user);
		return user;
	}
}
