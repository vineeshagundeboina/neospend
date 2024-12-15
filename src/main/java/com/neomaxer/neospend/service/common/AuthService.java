package com.neomaxer.neospend.service.common;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import com.neomaxer.neospend.controllers.auth.SignupRequest;
import com.neomaxer.neospend.exception.GenericException;
import com.neomaxer.neospend.models.auth.Profile;
import com.neomaxer.neospend.models.auth.Role;
import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.models.masters.Company;
import com.neomaxer.neospend.models.masters.Customer;
import com.neomaxer.neospend.pojos.requests.LoginRequest;
import com.neomaxer.neospend.pojos.response.SignupResponse;
import com.neomaxer.neospend.repositories.auth.RoleRepo;
import com.neomaxer.neospend.repositories.auth.UserRepo;
import com.neomaxer.neospend.repositories.masters.CompanyRepo;
import com.neomaxer.neospend.repositories.masters.CustomerRepo;
import com.neomaxer.neospend.repositories.masters.ProfileRepo;

@Service
public class AuthService {
	@Value("${app.frontEndResetUrl}")
	private String frontEndResetUrl;

	@Value("${app.frontEndVerifyEmailUrl}")
	private String frontEndVerifyEmailUrl;

	@Value("${app.fromEmail}")
	private String fromEmail;

	@Value("${app.storeRoleName}")
	private String storeRoleName;
	
	@Value("${app.comapnyRoleName}")
	private String comapnyRoleName;
	
	

	@Value("${app.distributorRoleName}")
	private String distributorRoleName;

	@Autowired
	private WebClientService webClinetService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private RoleRepo roleRepo;

	@Autowired
	private ProfileRepo profileRepo;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	JavaMailSender mailSender;

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;

	@Autowired
	private CustomerRepo customerRepo;

	public void createPasswordResetTokenForUser(User user, String token) {
		user.setResetToken(token);
		Date expDate = DateUtils.addMilliseconds(new Date(), 24 * 60 * 60 * 1000);
		user.setResetTokenExpiryDate(expDate);
		userRepo.save(user);
	}

//	public Profile createUser(LoginRequest loginRequest) {
//
//		Profile user = new Profile();
//		user.setPhone(loginRequest.getMobileNumber());
//
//		List<Role> roles = new ArrayList<>();
//		Role role = roleRepo.findByname("companyAdmin");
//		// Role role = roleRepo.findByRoleName("primary");
//		roles.add(role);
//
//		user.setRoles(roles);
//
//		return profileRepo.save(user);
//	}

	public void createVerificationTokenForUser(User user, String token) {
		user.setActivationToken(token);
		Date expDate = DateUtils.addMilliseconds(new Date(), 24 * 60 * 60 * 1000);
		user.setEmailTokenExpiryDate(expDate);
		userRepo.save(user);
	}

//	public void sendVerifyTokenEmail(String token, User user) {
//		try {
//			final String url = frontEndVerifyEmailUrl + "?token=" + token;
//			Context thymeleafContext = new Context();
//			Map<String, Object> templateModel = new HashMap<String, Object>();
//			templateModel.put("token_link", url);
//			thymeleafContext.setVariables(templateModel);
//			String htmlBody = thymeleafTemplateEngine.process("confirm-email.html", thymeleafContext);
//			MimeMessage email = constructHtmlEmail("Verify Email", htmlBody, user);
//			mailSender.send(email);
//
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new GenericException("Unable to email " + e.getMessage());
//		}
	//}

	/*
	 * public boolean signUpp(SignupRequest signupRequest) {
	 * 
	 * try {
	 * 
	 * Company company = new Company();
	 * company.setCompanyName(signupRequest.getCompanyName());
	 * companyRepo.save(company);
	 * 
	 * User user = createUser(signupRequest);
	 * 
	 * String token = UUID.randomUUID().toString();
	 * createVerificationTokenForUser(user, token);
	 * 
	 * 
	 * if (signupRequest.getType().equals("distributor")) { Distributor distributor
	 * = new Distributor();
	 * distributor.setDistributorName(signupRequest.getCompanyName() +
	 * " Distributor"); distributor.setCompany(company);
	 * distributor.setDistributorMail(signupRequest.getEmail());
	 * distributor.setDistributorPhone(signupRequest.getMobileNumber());
	 * distributorRepo.save(distributor);
	 * 
	 * DistributorUser distributorUser = new DistributorUser();
	 * distributorUser.setCompany(company); distributorUser.setUser(user);
	 * distributorUser.setEmail(signupRequest.getEmail());
	 * distributorUser.setDistributor(distributor);
	 * distributorUser.setCompanyAdmin(true);
	 * distributorUserRepo.save(distributorUser);
	 * 
	 * String whName = (distributor.getCompany().getCompanyName() +
	 * "_Warehouse").replace(" ", "_"); Warehouse warehouse = new Warehouse();
	 * warehouse.setWarehouseName(whName);
	 * warehouse.setCompany(distributor.getCompany());
	 * warehouse.setDistributor(distributor); warehouseRepo.save(warehouse);
	 * 
	 * //sendVerifyTokenEmail(token, user);
	 * webClinet.sendOTP(user.getMobileNumber()); return true; }
	 * 
	 * } catch (Exception e) { e.printStackTrace(); return false; } return true; }
	 */

	public void sendResetTokenEmail(String token, User user) {
		try {
			final String url = frontEndResetUrl + "?token=" + token;
			Context thymeleafContext = new Context();
			Map<String, Object> templateModel = new HashMap<String, Object>();
			templateModel.put("token_link", url);
			thymeleafContext.setVariables(templateModel);
			String htmlBody = thymeleafTemplateEngine.process("reset-password.html", thymeleafContext);
			MimeMessage email = constructHtmlEmail("Your Reset Password Link", htmlBody, user);
			mailSender.send(email);

		} catch (Exception e) {
			e.printStackTrace();
			throw new GenericException("Unable to email " + e.getMessage());
		}
	}

	@SuppressWarnings("unused")
	private SimpleMailMessage constructEmail(String subject, String body, User user) {
		if (user.getEmail() == null) {
			throw new GenericException("Email id not found for user");
		}
		SimpleMailMessage email = new SimpleMailMessage();
		email.setSubject(subject);
		email.setText(body);
		email.setTo(user.getEmail());
		email.setFrom(fromEmail);
		return email;
	}

	private MimeMessage constructHtmlEmail(String subject, String body, User user) throws MessagingException {
		if (user.getEmail() == null) {
			throw new GenericException("Email id not found for user");
		}

		MimeMessage mail = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true, "utf-8");
		helper.setSubject(subject);
		helper.setText(body, true); // Use this or above line.
		helper.setTo(user.getEmail());
		helper.setFrom(fromEmail);

		return mail;
	}

	public String validatePasswordResetToken(String passToken) {
		if (passToken == null) {
			throw new GenericException("Unable to update password, token not sent.");
		}
		Optional<User> user = userRepo.findByResetToken(passToken);
		if (user.isEmpty()) {
			throw new GenericException("Unable to update password, token not found.");
		}
		boolean isTokenExpired = user.get().getResetTokenExpiryDate().before(new Date());
		return !user.isPresent() ? "invalidToken" : isTokenExpired ? "expired" : null;
	}

	public void resetPassword(String token, String password) {
		try {
			User user = userRepo.findByResetToken(token).get();
			String newPassword = bCryptPasswordEncoder.encode(password);
			user.setPassword(newPassword);
			user.setResetToken(null);
			user.setResetTokenExpiryDate(null);
			userRepo.save(user);
		} catch (Exception e) {
			throw new GenericException("Unable to update password, please retry.");
		}
	}

	public void changePassword(String password, User user) {
		try {
			String newPassword = bCryptPasswordEncoder.encode(password);
			user.setPassword(newPassword);
			user.setResetToken(null);
			user.setResetTokenExpiryDate(null);
			userRepo.save(user);
		} catch (Exception e) {
			throw new GenericException("Unable to update password, please retry.");
		}
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

	public boolean signUp(SignupRequest signupRequest) {

		try {

			Company company = new Company();
			company.setCompanyName(signupRequest.getCompanyName());
			company.setDealerCode(signupRequest.getPartnerCode());
			company.setCompanyPanNumber(signupRequest.getPanNumber());
			companyRepo.save(company);

			User user = createUser(signupRequest);
			
			// TODO: Create a profile -  assoc. user & company
			
			Profile profile = new Profile();
			profile.setCompany(company);
			profile.setIsActive(true);
			profile.setUser(user);
			profile.setCompanyAdmin(true);
			profileRepo.save(profile);
//			
//			List<Role> roles = new ArrayList<Role>();
//			if (signupRequest.getType().equals("store")) {
//				Role role = roleRepo.findByname(storeRoleName);
//				roles.add(role);
//			}	
			
			List<Role> roles=new ArrayList<Role>();
			if(signupRequest.getType().equals("superadmin")) {
				Role role= roleRepo.findByname(comapnyRoleName);
				roles.add(role);
			}
			


			String token = UUID.randomUUID().toString();
			createVerificationTokenForUser(user, token);

			//sendVerifyTokenEmail(token, user);
			webClinetService.sendOTP(user.getMobileNumber());
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	
		
	}
	
	

//			User user = createUser(signupRequest);
//
//			String token = UUID.randomUUID().toString();
//			createVerificationTokenForUser(user, token);
//
//			if (signupRequest.getType().equals("store")) {
//				Store store = new Store();
//				store.setStoreName(signupRequest.getCompanyName());
//				store.setCompany(company);
//				store.setStoreMail(signupRequest.getEmail());
//				store.setStorePhone(signupRequest.getMobileNumber());
//				storeRepo.save(store);
//
//				StoreUser storeUser = new StoreUser();
//				storeUser.setCompany(company);
//				storeUser.setUser(user);
//				storeUser.setEmail(signupRequest.getEmail());
//				storeUser.setStore(store);
//				storeUser.setCompanyAdmin(true);
//				storeUserRepo.save(storeUser);
//
//				sendVerifyTokenEmail(token, user);
//				webClinetService.sendOTP(user.getMobileNumber());
//				return true;
//			}
//			if (signupRequest.getType().equals("distributor")) {
//				Distributor distributor = new Distributor();
//				distributor.setDistributorName(signupRequest.getCompanyName());
//				distributor.setCompany(company);
//				distributor.setDistributorMail(signupRequest.getEmail());
//				distributor.setDistributorPhone(signupRequest.getMobileNumber());
//				distributorRepo.save(distributor);
//
//				DistributorUser distributorUser = new DistributorUser();
//				distributorUser.setCompany(company);
//				distributorUser.setUser(user);
//				distributorUser.setEmail(signupRequest.getEmail());
//				distributorUser.setDistributor(distributor);
//				distributorUser.setCompanyAdmin(true);
//				distributorUserRepo.save(distributorUser);
//
//				String whName = (distributor.getCompany().getCompanyName() + "_Warehouse").replace(" ", "_");
//				Warehouse warehouse = new Warehouse();
//				warehouse.setWarehouseName(whName);
//				warehouse.setCompany(distributor.getCompany());
//				warehouse.setDistributor(distributor);
//				warehouseRepo.save(warehouse);
//
//				sendVerifyTokenEmail(token, user);
//				webClinetService.sendOTP(user.getMobileNumber());
//				return true;
//			}
//
//		} 

	public User createUser(SignupRequest signupRequest) {
		User user = new User();
		user.setIsActive(false);
		user.setUserName(signupRequest.getMobileNumber());
		user.setMobileNumber(signupRequest.getMobileNumber());
		user.setEmail(signupRequest.getEmail());
		String pwd = signupRequest.getPassword();
		pwd = bCryptPasswordEncoder.encode(pwd);
		user.setPassword(pwd);
		
		List<Role> roles = new ArrayList<Role>();
		if (signupRequest.getType().equals("store")) {
			Role role = roleRepo.findByname(storeRoleName);
			roles.add(role);
		}
		
		if (signupRequest.getType().equals("distributor")) {
			Role role = roleRepo.findByname(distributorRoleName);
			roles.add(role);
		}

		user.setRoles(roles);
		userRepo.save(user);
		return user;
	}

	public User createCustomerUser(SignupRequest signupRequest) {
		User user = new User();
		user.setIsActive(true);
		user.setUserName(signupRequest.getMobileNumber());
		user.setMobileNumber(signupRequest.getMobileNumber());
		// user.setEmail(signupRequest.getEmail());
		// String pwd = signupRequest.getPassword();
		// pwd = bCryptPasswordEncoder.encode(pwd);
		// user.setPassword(pwd);
		// List<Role> roles = new ArrayList<Role>();

		userRepo.save(user);
		return user;
	}

	public boolean resendActivationLink(String email) {
		Optional<User> user = userRepo.findFirstByEmail(email);
		if (user.isEmpty()) {
			throw new GenericException("Email not found, please sign up again.");
		}
		String token = UUID.randomUUID().toString();
		createVerificationTokenForUser(user.get(), token);
		//sendVerifyTokenEmail(token, user.get());
		return true;
	}

	public SignupResponse validateUser(LoginRequest loginRequest) {
		SignupResponse response = new SignupResponse();

		try {

			Optional<Profile> existingUser = profileRepo.findByphone(loginRequest.getMobileNumber());
			if (existingUser.isPresent()) {
				// send otp

				// response = webClient.sendOTP(loginRequest.getMobileNumber());

				// Mock
				response = webClinetService.mockSignupResponse();

				// checking if mpin exists
				if (existingUser.get().getMpin() != null) {
					response.setIsMPINExists(true);
				}

				// checking if profile exists
				Optional<Profile> existingProfile = profileRepo.findById(existingUser.get().getId());
				if (existingProfile != null) {
					response.setIsProfileExists(true);
				}

			} else {
				
				response = webClinetService.mockSignupResponse();

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return response;

	}
}
