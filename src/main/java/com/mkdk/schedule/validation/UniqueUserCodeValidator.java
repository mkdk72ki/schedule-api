package com.mkdk.schedule.validation;

import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.mapper.UserMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class UniqueUserCodeValidator implements ConstraintValidator<UniqueUserCode, String> {

  private final UserMapper userMapper;
  @Autowired
  private SecuritySession securitySession;

  public UniqueUserCodeValidator(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Optional<User> user = userMapper.findByCode(value);
    String userCode = userMapper.findCode(securitySession.getUserId());
  if (userCode.equals(value)){
    return true;
  }
    return user.isEmpty();
  }
}
