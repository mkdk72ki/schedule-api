package com.mkdk.schedule.controller.form;

public class UserCreateForm {

  private String userName;

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
