package com.valid.model.util;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NotSpaceValidator implements ConstraintValidator<NotSpace, String> {

	@Override
	public boolean isValid(String value
	 , ConstraintValidatorContext context) {
		
		return value != null ? !value.matches("^.*[ ]+.*$") : false;
	}
	
}
