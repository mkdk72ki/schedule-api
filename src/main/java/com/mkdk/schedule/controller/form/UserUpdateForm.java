package com.mkdk.schedule.controller.form;

import com.mkdk.schedule.validation.UniqueUserCode;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UserUpdateForm {

  @Length(max = 20, message = "20字以内で入力してください")
  private String userName;

  @Length(min = 5, max = 20, message = "5字以上20字以内で入力してください")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字のみで入力してください")
  @UniqueUserCode
  private String userCode;

  @Length(min = 5, max = 50, message = "5字以上50字以内で入力してください")
  @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "半角英数字のみで入力してください")
  private String userPassword;

  private String authority;

  public UserUpdateForm(String userName, String userCode, String userPassword, String authority) {
    this.userName = userName;
    this.userCode = userCode;
    this.userPassword = userPassword;
    this.authority = authority;
  }

  public String getUserName() {
    return userName;
  }

  public String getUserCode() {
    return userCode;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public String getAuthority() {
    return authority;
  }
}
