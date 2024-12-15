package com.neomaxer.neospend.validatiors;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.neomaxer.neospend.validatiors.annotations.IPValidation;



public class IPAddressValidator implements ConstraintValidator<IPValidation, String> {

	private static final String IPADDRESS_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\." + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
			+ "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

	private Pattern pattern;
	private Matcher matcher;

	@Override
	public void initialize(IPValidation contraintAnnotation) {

	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		boolean status = validate(value);
		// System.out.println(context.getDefaultConstraintMessageTemplate() + ":" +
		// value);
		return status;
	}

	public boolean validate(final String ip) {
		pattern = Pattern.compile(IPADDRESS_PATTERN);
		matcher = pattern.matcher(ip);
		return matcher.matches();
	}

}
