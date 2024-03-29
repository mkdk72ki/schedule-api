package com.mkdk.schedule.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueGroupCodeValidator.class)
public @interface UniqueGroupCode {
  String message() default "入力されたIDはすでに登録されています。別のIDを入力してください";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
