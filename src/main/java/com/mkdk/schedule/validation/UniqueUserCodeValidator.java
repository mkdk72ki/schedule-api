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
    Integer userId = securitySession.getUserId();
    if (userId == null) {
      return user.isEmpty();
    } else if (userMapper.findCode(userId).equals(value)) {
      return true;
    } else {
      return user.isEmpty();
    }
  }
}
