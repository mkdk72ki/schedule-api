package com.mkdk.schedule.controller.form;

public class UserUpdateForm {

  private String userName;

  private String userPassword;

  public UserUpdateForm(String userName, String userPassword) {
    this.userName = userName;
    this.userPassword = userPassword;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }
}
