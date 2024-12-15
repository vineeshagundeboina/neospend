
package com.neomaxer.neospend.service.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.neomaxer.neospend.models.auth.User;
import com.neomaxer.neospend.pojos.response.OtpResponse;
import com.neomaxer.neospend.pojos.response.SignupResponse;
import com.neomaxer.neospend.repositories.auth.UserRepo;

@Service
public class WebClientService {

	private final UserRepo userRepo;

	@Value("${app.token.authKey}")
	private String authkey;

	@Value("${app.token.template}")
	String template;

	@Autowired
	public WebClientService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}

	public String sendOTP(String mobile) throws Exception {
		String output = null;
		URLConnection myURLConnection = null;
		String mainUrl = "https://api.msg91.com/api/v5/otp?";
		StringBuilder sbPostData = new StringBuilder(mainUrl);
		sbPostData.append("template_id=" + template);
		sbPostData.append("&mobiles=" + mobile);
		sbPostData.append("&authkey=" + authkey);
		sbPostData.append("&otp_length=" + 6);
		mainUrl = sbPostData.toString();

		URL myURL = null;
		BufferedReader reader = null;
		try {
			myURL = new URL(mainUrl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			String response;
			while ((response = reader.readLine()) != null)
				output = response;

			reader.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return output;
	}

	public OtpResponse reSendOTP(String mobile) throws Exception {
		@SuppressWarnings("unused")
		String output = null;
		URLConnection myURLConnection = null;
		String mainUrl = "https://api.msg91.com/api/v5/otp/retry?";
		StringBuilder sbPostData = new StringBuilder(mainUrl);
		sbPostData.append("&retrytype=" + "text");
		sbPostData.append("&authkey=" + authkey);
		sbPostData.append("&mobile=" + mobile);
		mainUrl = sbPostData.toString();
		URL myURL = null;
		OtpResponse obj = null;
		BufferedReader reader = null;
		try {
			myURL = new URL(mainUrl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			String response;
			while ((response = reader.readLine()) != null)
				output = response;
			obj = new Gson().fromJson(response, OtpResponse.class);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();

		}
		return obj;
	}

	public OtpResponse verifyOTP(String mobile, int otp) throws Exception {
		URLConnection myURLConnection = null;
		String mainUrl = "https://api.msg91.com/api/v5/otp/verify?";
		StringBuilder sbPostData = new StringBuilder(mainUrl);
		sbPostData.append("otp=" + otp);
		sbPostData.append("&authkey=" + authkey);
		sbPostData.append("&mobile=" + mobile);
		mainUrl = sbPostData.toString();
		String output = null;
		URL myURL = null;
		OtpResponse obj = null;

		BufferedReader reader = null;
		try {
			myURL = new URL(mainUrl);
			myURLConnection = myURL.openConnection();
			myURLConnection.connect();
			reader = new BufferedReader(new InputStreamReader(myURLConnection.getInputStream()));
			String response;
			while ((response = reader.readLine()) != null) {
				output = response;
				System.out.println(output);
				obj = new Gson().fromJson(response, OtpResponse.class);
			}
			reader.close();
		} catch (IOException e) {
			System.out.println(e);
		}
		return obj;
	}

	public OtpResponse checkAndcreateUser(String mobile, String fcmToken) throws Exception {

		User user = userRepo.findByMobileNumber(mobile);
		if (fcmToken != null && fcmToken != "") {
			user.setFcmToken(fcmToken);
			userRepo.save(user);
		}

		String clinetResponse = sendOTP(mobile);
		OtpResponse otpResponse = new OtpResponse();
		otpResponse.setType(clinetResponse);
		otpResponse.setMessage("OTP has been sent successfully");

		return otpResponse;
	}

	public SignupResponse mockSignupResponse() {
		SignupResponse signupResponse = new SignupResponse();
		signupResponse.setStatus(true);
		return signupResponse;
	}

}
