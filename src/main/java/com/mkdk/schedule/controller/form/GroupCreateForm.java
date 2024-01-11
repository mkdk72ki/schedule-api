package com.mkdk.schedule.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class GroupCreateForm {

  @NotBlank(message = "入力してください")
  @Length(max = 20, message = "20字以内で入力してください")
  private String groupName;

  @Length(min = 5, max = 20, message = "5文字以上20字以内で入力してください")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字のみで入力してください")
  private String groupCode;

  @Length(min = 5, max = 30, message = "5字以上30字以内で入力してください")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字のみで入力してください")
  private String groupPassword;

  public GroupCreateForm(String groupName, String groupCode, String groupPassword) {
    this.groupName = groupName;
    this.groupCode = groupCode;
    this.groupPassword = groupPassword;
  }

  public String getGroupName() {
    return groupName;
  }

  public String getGroupCode() {
    return groupCode;
  }

  public String getGroupPassword() {
    return groupPassword;
  }
}
