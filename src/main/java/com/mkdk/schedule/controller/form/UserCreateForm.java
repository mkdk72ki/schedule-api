package com.mkdk.schedule.controller.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserCreateForm {

  @NotBlank(groups = ValidGroup1.class, message = "入力してください")
  @Length(groups = ValidGroup1.class, max = 20, message = "20字以内で入力してください")
  private String userName;

  @NotBlank(groups = ValidGroup2.class, message = "入力してください")
  @Length(groups = ValidGroup1.class, min = 5, max = 20, message = "5字以上20字以内で入力してください")
  @Pattern(regexp = "^[a-zA-Z0-9]{5,20}$",groups = ValidGroup2.class, message = "半角英数字のみで入力してください")
  private String userPassword;

  public UserCreateForm(String userName, String userPassword) {
    this.userName = userName;
    this.userPassword = userPassword;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserPassword() {
    return userPassword;
  }
}
