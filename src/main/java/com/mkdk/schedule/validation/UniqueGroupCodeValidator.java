package com.mkdk.schedule.validation;

import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.mapper.GroupMapper;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Objects;
import java.util.Optional;

public class UniqueGroupCodeValidator implements ConstraintValidator<UniqueGroupCode, String> {

  private final GroupMapper groupMapper;

  public UniqueGroupCodeValidator(GroupMapper groupMapper) {
    this.groupMapper = groupMapper;
  }

  @Override
  public boolean isValid(String value, ConstraintValidatorContext context) {
    Optional<Group> group = groupMapper.findByCode(value);

    if (group.isPresent()){
      String groupCode = groupMapper.findCode(group.get().getGroupId());
      return groupCode.matches(value);
    }  return group.isEmpty();
}
}
