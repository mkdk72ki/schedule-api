package com.mkdk.schedule.entity;

public class User {

  private Integer userId;

  private String userName;

  private String userCode;

  private String userPassword;

  private Authority authority;

  public enum Authority {
    ADMIN, USER
  }

  public User(Integer userId, String userName, String userCode, String userPassword, Authority authority) {
    this.userId = userId;
    this.userName = userName;
    this.userCode = userCode;
    this.userPassword = userPassword;
    this.authority = authority;
  }

  public Integer getUserId() {
    return userId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getUserCode() {
    return userCode;
  }

  public void setUserCode(String userCode) {
    this.userCode = userCode;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  public Authority getAuthority() {
    return authority;
  }

  public void setAuthority(Authority authority) {
    this.authority = authority;
  }

  public void update(String userName, String userCode, String userPassword) {
    if (userName != null) {
      this.setUserName(userName);
    }
    if (userCode != null) {
      this.setUserCode(userCode);
    }
    if (userPassword != null) {
      this.setUserPassword(userPassword);
    }
  }

}
