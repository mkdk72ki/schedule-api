package com.mkdk.schedule.validation;

import com.mkdk.schedule.mapper.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueCodeValidator implements ConstraintValidator<UniqueCode, String> {

  private final UserMapper userMapper;

  public UniqueCodeValidator(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    return userMapper.findByCode(value).isEmpty();
  }
}
