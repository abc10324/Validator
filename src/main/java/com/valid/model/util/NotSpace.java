package com.valid.model.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy=NotSpaceValidator.class)
@Retention(RUNTIME)
@Target({FIELD,PARAMETER})
public @interface NotSpace {
  String message() default
    "input can't have white space";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
