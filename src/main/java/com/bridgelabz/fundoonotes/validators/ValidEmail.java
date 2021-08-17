package com.bridgelabz.fundoonotes.validators;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;


@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD})
@Constraint(validatedBy = EmailValidator.class)
public @interface ValidEmail {

	String message() default "{email.invalid}";

	 Class<?>[] groups() default {};

	 Class<? extends Payload>[] payload() default {};
}
