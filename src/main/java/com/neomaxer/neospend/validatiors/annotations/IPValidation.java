package com.neomaxer.neospend.validatiors.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.neomaxer.neospend.validatiors.IPAddressValidator;

@Documented
@Constraint(validatedBy = IPAddressValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, ANNOTATION_TYPE })
public @interface IPValidation {
	String message() default "Invalid IP Address";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}