package com.neomaxer.neospend.controllers;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.neomaxer.neospend.config.CurrentUser;
import com.neomaxer.neospend.config.JdbcUserDetailsService;
import com.neomaxer.neospend.controllers.auth.SignupRequest;
import com.neomaxer.neospend.exception.GenericException;
import com.neomaxer.neospend.exception.RecordNotFoundException;
import com.neomaxer.neospend.exception.TokenRefreshException;
import com.neomaxer.neospend.models.auth.RefreshToken;
import com.neomaxer.neospend.models.auth.SendOtp;
import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.models.auth.VerifyOtp;
import com.neomaxer.neospend.models.masters.Company;
import com.neomaxer.neospend.models.masters.Customer;
import com.neomaxer.neospend.models.masters.Partner;
import com.neomaxer.neospend.pojos.requests.AuthenticationRequest;
import com.neomaxer.neospend.pojos.requests.ChangePasswordRequest;
import com.neomaxer.neospend.pojos.requests.LoginRequest;
import com.neomaxer.neospend.pojos.requests.MPin;
import com.neomaxer.neospend.pojos.requests.PasswordRequest;
import com.neomaxer.neospend.pojos.requests.TokenRefreshRequest;
import com.neomaxer.neospend.pojos.response.AuthenticationResponse;
import com.neomaxer.neospend.pojos.response.MessageResponse;
import com.neomaxer.neospend.pojos.response.OtpResponse;
import com.neomaxer.neospend.pojos.response.SignupResponse;
import com.neomaxer.neospend.pojos.response.TokenRefreshResponse;
import com.neomaxer.neospend.repositories.auth.UserRepo;
import com.neomaxer.neospend.repositories.masters.CompanyRepo;
import com.neomaxer.neospend.repositories.masters.PartnersRepo;
import com.neomaxer.neospend.service.common.AuthService;
import com.neomaxer.neospend.service.common.RefreshTokenService;
import com.neomaxer.neospend.service.common.WebClientService;
import com.neomaxer.neospend.utils.JwtUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/auth")
@Tag(name = "User Authentication", description = "APIs for User Authentication related operations")
@ComponentScan("com.neomaxer.n.service.auth")
public class AuthController {
	// private final Logger log4j = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private JdbcUserDetailsService jdbcUserDetailsService;

	@Autowired
	private JwtUtil jwtTokenUtil;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private AuthService authService;

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private WebClientService webClientService;

	@Autowired
	private PartnersRepo partnerRepo;

	@Autowired
	private final TemplateEngine templateEngine;

	@Autowired
	private CompanyRepo companyRepo;

	@Autowired
	public AuthController(TemplateEngine templateEngine) {
		this.templateEngine = templateEngine;

	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		// System.out.println(authenticationRequest.toString());
		try {
			String jwt = "";
			@SuppressWarnings("unused")
			Optional<User> oldUser = userRepo.findByUserName(authenticationRequest.getUsername());
			// JDBC Check BCrypt the password
			// log4j.debug("Inside JDBC check");
			CurrentUser userDetails = null;
			try {
				userDetails = jdbcUserDetailsService.loadUserByUsernameAndPass(authenticationRequest.getUsername(),
						authenticationRequest.getPassword());
			} catch (Exception e) {
				throw new RecordNotFoundException("Authentication Failed " + e.getMessage());
			}

			RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

			jwt = jwtTokenUtil.generateToken(userDetails);
			AuthenticationResponse response = new AuthenticationResponse(jwt, refreshToken.getToken());

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

	}

	@PostMapping("/posauthenticate")
	public ResponseEntity<?> createPosAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest)
			throws Exception {

		try {
			String jwt = "";
			@SuppressWarnings("unused")
			Optional<User> oldUser = userRepo.findByUserName(authenticationRequest.getUsername());

			// JDBC Check BCrypt the password
			// log4j.debug("Inside JDBC check");
			CurrentUser userDetails = null;
			try {
				userDetails = jdbcUserDetailsService.loadUserByUsernameAndPass(authenticationRequest.getUsername(),
						authenticationRequest.getPassword());
			} catch (Exception e) {
				throw new RecordNotFoundException("Authentication Failed " + e.getMessage());
			}

//			StoreUser storeUser = storeUserRepo.findByUserId(userDetails.getId()).get();
//
//			if (storeUser.getCompany() == null) {
//				throw new GenericException("Company is not associated");
//			}
//			if (!storeUser.getCompany().isSubscriptionActive()) {
//				throw new GenericException("Subscription Inactive");
//			}

			RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

			jwt = jwtTokenUtil.generateToken(userDetails);
			AuthenticationResponse response = new AuthenticationResponse(jwt, refreshToken.getToken());

			return ResponseEntity.ok(response);

		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

	}

//	@PostMapping("/savempin")
//	@Operation(summary = "Save MPIN in the User")
//	public ResponseEntity<Profile> saveMpin(@RequestBody MPin mpin) {
//		Optional<Profile> profile = profileRepo.findById(mpin.getUserId());
//		profile.get().setMpin(mpin.getMpin());
//		return ResponseEntity.ok(profileRepo.save(profile.get()));
//	}
//
//	@PostMapping("/validatempin")
//	@Operation(summary = "Validate MPIN of the User")
//	public ResponseEntity<String> validatempin(@RequestBody Mpin mpin) {
//		Optional<Profile> profile = profileRepo.findByIdAndMpin(mpin.getUserId(), mpin.getMpin());
//
//		if (profile.isPresent()) {
//			return ResponseEntity.ok("MPIN validated");
//		}
//		return ResponseEntity.badRequest().body("Invalid MPIN");
//	}

//	@GetMapping("/verifyemail")
//	public ResponseEntity<?> verifyemail(@RequestParam("token") String token) {
//		Optional<User> user = userRepo.findByActivationToken(token);
//		if (user.isEmpty()) {
//			throw new GenericException("Invalid Token");
//		} else {
//			User _user = user.get();
//			if (_user.getIsActive()) {
//				return ResponseEntity.ok(new MessageResponse("User is already active"));
//			}
//			Date dt = _user.getEmailTokenExpiryDate();
//			if (dt.before(new Date())) {
//				throw new GenericException("Token Expired");
//			}
//			_user.setIsActive(true);
//			_user.setEmailVerified(true);
//			userRepo.save(_user);
//		}
//
//		return ResponseEntity.ok(new MessageResponse("Email Verification Success"));
//	}

	@GetMapping("/resendVerifyEmail")
	public ResponseEntity<?> resendVerifyEmail(@RequestParam("email") String email) {
		authService.resendActivationLink(email);
		return ResponseEntity.ok(new MessageResponse("Email Verification Resent"));
	}

	@GetMapping("/resetPassword")
	public MessageResponse resetPassword(@RequestParam("username") String username) {
		Optional<User> user = userRepo.findByUserName(username);
		if (user.isEmpty()) {
			throw new GenericException("User Not Found");

		}
		String token = UUID.randomUUID().toString();
		authService.createPasswordResetTokenForUser(user.get(), token);
		authService.sendResetTokenEmail(token, user.get());
		return new MessageResponse("Reset Password Link Sent to Email");
	}

	@PostMapping("/savePassword")
	public MessageResponse savePassword(@RequestBody @Valid PasswordRequest passwordRequest) {

		final String result = authService.validatePasswordResetToken(passwordRequest.getToken());

		if (result != null) {
			return new MessageResponse("Cannot reset password " + result);
		} else {
			authService.resetPassword(passwordRequest.getToken(), passwordRequest.getNewPassword());
			return new MessageResponse("Reset password Successful");
		}

	}

	@PostMapping("/changePassword")
	public MessageResponse changePassword(@RequestBody ChangePasswordRequest passwordRequest) {

		Optional<User> user = userRepo.findById(passwordRequest.getUser().getId());
		if (user.isPresent()) {
			authService.changePassword(passwordRequest.getNewPassword(), user.get());
			return new MessageResponse("Reset password Successful");
		} else {
			return new MessageResponse("Cannot reset password user not found");
		}

	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@RequestBody TokenRefreshRequest request) {
		System.out.println("entered");
		String requestRefreshToken = request.getRefreshToken();
		System.out.println("before requestRefreshToken" + requestRefreshToken);
		Optional<RefreshToken> refreshToken = refreshTokenService.findByToken(requestRefreshToken);
		System.out.println("requestRefreshToken" + requestRefreshToken);
		if (refreshToken.isPresent()) {
			refreshTokenService.verifyExpiration(refreshToken.get());
			String token = jwtTokenUtil
					.generateToken(jdbcUserDetailsService.getCurrentUser(refreshToken.get().getUser()));
			return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
		} else {
			throw new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!");
		}

	}

	@PostMapping("/signout")
	public ResponseEntity<?> logoutUser() {
		CurrentUser userDetails = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UUID userId = userDetails.getId();
		refreshTokenService.deleteByUserId(userId);
		return ResponseEntity.ok(new MessageResponse("Log out successful!"));
	}

	@Transactional
	@PostMapping("/signup")
	public ResponseEntity<?> signupWithEmail(@RequestBody SignupRequest signupRequest) {

		// TODO validate if mobile number already exists and reject signup ask to sign
		// in instead
		Optional<User> user = userRepo.findByUserName(signupRequest.getMobileNumber());
		Optional<Company> existingCompany = companyRepo.findByCompanyPanNumber(signupRequest.getPanNumber());

		if (user.isPresent()) {
			throw new GenericException("Mobile Number already exists sign in instead");
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
		boolean status = authService.signUp(signupRequest);

		if (!status) {
			throw new GenericException("Could not complete sign up");
		}

		return ResponseEntity.ok(new MessageResponse("Signup Success, verify email"));
	}

	@Transactional
	@PostMapping("/customer")
	public ResponseEntity<?> createCustomer(@RequestBody SignupRequest signupRequest) {
		Customer customer = authService.createCustomer(signupRequest);
		if (customer == null || customer.equals("null")) {
			throw new GenericException("Could not complete sign up");
		}

		// TODO: send otp to the customer mobile number

		return ResponseEntity.ok(customer);
	}

	@SuppressWarnings("unused")
	private String getUPIString(String payeeAddress, String payeeName, String payeeMCC, String trxnID, String trxnRefId,
			String trxnNote, String payeeAmount, String currencyCode, String refUrl) {
		String UPI = "upi://pay?pa=" + payeeAddress + "&pn=" + payeeName + "&mc=" + payeeMCC + "&tid=" + trxnID + "&tr="
				+ trxnRefId + "&tn=" + trxnNote + "&am=" + payeeAmount + "&cu=" + currencyCode + "&refUrl=" + refUrl;
		return UPI.replace(" ", "+");
	}

	@PostMapping("/sendotp")
	public OtpResponse sendOtp(@RequestBody SendOtp sendOtp) throws Exception {
		// System.out.println(sendOtp.getMobile()+" "+ sendOtp.getFcmToken());
		// sendOtp.getUsername()
		return webClientService.checkAndcreateUser(sendOtp.getMobile(), sendOtp.getFcmToken());
	}

	@Transactional
	@PostMapping("/signin")
	public ResponseEntity<?> sendOtp(@RequestBody LoginRequest loginRequest) throws Exception {
		return ResponseEntity.ok(authService.validateUser(loginRequest));
	}

	@PostMapping("/verifyotp")
	public ResponseEntity<?> verifyOtp(@RequestBody VerifyOtp verifyOtp) throws Exception {
		OtpResponse otpResponse = new OtpResponse();
		otpResponse = (webClientService.verifyOTP((verifyOtp.getMobileNumber()), (verifyOtp.getOtp())));
		System.out.println(otpResponse.toString());
		if (otpResponse.getType().equalsIgnoreCase("success")) {
			User user1 = userRepo.findByMobileNumber(verifyOtp.getMobileNumber());
			if (user1.getIsActive()) {
				return ResponseEntity.ok(new MessageResponse("User is already active"));
			}
			user1.setMobileVerified(true);
			user1.setIsActive(true);
			userRepo.save(user1);
			otpResponse.setType(otpResponse.getType());
			otpResponse.setMessage(otpResponse.getMessage());
			return ResponseEntity.ok(otpResponse);
		} else {
			otpResponse.setType(otpResponse.getType());
			otpResponse.setMessage(otpResponse.getMessage());
			return ResponseEntity.ok(otpResponse);
		}

	}

	@GetMapping("/resendotp/{mobile}")
	public OtpResponse resendOtp(@PathVariable String mobile) throws Exception {
		return webClientService.reSendOTP(mobile);
	}

	@PostMapping("/savempin")
	public ResponseEntity<User> saveMpin(@RequestBody MPin mpin) throws Exception {
		Optional<User> user = userRepo.findById(mpin.getUserId());
		user.get().setMpin(mpin.getMpin());
		return ResponseEntity.ok(userRepo.save(user.get()));
	}

	@PostMapping("/validatempin")
	public ResponseEntity<Integer> validatempin(@RequestBody MPin mpin) throws Exception {
		Optional<User> user = userRepo.findByIdAndMpin(mpin.getUserId(), mpin.getMpin());
		if (user.get() != null) {
			return ResponseEntity.ok(HttpStatus.SC_OK);
		}
		return ResponseEntity.ok(HttpStatus.SC_NOT_FOUND);
	}

	@PostMapping("/resetmpin")
	public OtpResponse resetmpin(@RequestBody MPin mpin) throws Exception {
		Optional<User> user = userRepo.findById(mpin.getUserId());
		if (user.get() != null) {
			return webClientService.checkAndcreateUser(user.get().getMobileNumber(), user.get().getFcmToken());
		}
		return null;
	}

	@PostMapping("/forgotmpin")
	public OtpResponse forgotmpin(@PathVariable UUID userId) throws Exception {
		Optional<User> user = userRepo.findById(userId);
		return webClientService.checkAndcreateUser(user.get().getMobileNumber(), user.get().getFcmToken());
	}

	@PostMapping("/verifyCustomerOTP")
	@Operation(summary = "Verify Customer OTP")
	public ResponseEntity<?> verifyCustomerOtp(@RequestBody VerifyOtp verifyOtp) {
		// SignupResponse signResponse =
		// webClient.verifyOtp(verifyOtp.getMobileNumber(), verifyOtp.getOtp());

		// Mock
		// SignupResponse signResponse = webClientService.mockSignupResponse();
		SignupResponse signupResponse = new SignupResponse();
		signupResponse.setStatus(true);
		// try {
		String jwt = "";
		Optional<User> oldUser = userRepo.findByUserName(verifyOtp.getMobileNumber());

		if (signupResponse.getStatus() && oldUser.isPresent()) {
			// set user as active
			User user = oldUser.get();
			user.setIsActive(true);
			userRepo.save(user);
		}

		CurrentUser userDetails = null;

		userDetails = jdbcUserDetailsService.loadCustomerUserByUsername(verifyOtp.getMobileNumber());

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		jwt = jwtTokenUtil.generateToken(userDetails);
		AuthenticationResponse response = new AuthenticationResponse(jwt, refreshToken.getToken());

		return ResponseEntity.ok(response);
	}
}

// } catch (Exception e) {
// throw new GenericException("Something went wrong. Please try again", e);
// }
