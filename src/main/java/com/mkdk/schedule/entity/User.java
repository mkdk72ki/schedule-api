package com.mkdk.schedule.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;


public class User implements UserDetails {

  private Integer userId;

  private String userName;

  private String userCode;

  private String userPassword;

  private Authority authority;

  public User(Integer userId, String userName, String userCode, String userPassword, Authority authority) {
    this.userId = userId;
    this.userName = userName;
    this.userCode = userCode;
    this.userPassword = userPassword;
    this.authority = authority;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public String getPassword() {
    return null;
  }

  @Override
  public String getUsername() {
    return userCode;
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
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

  public void update(String userName, String userCode, String userPassword, String authority) {
    if (userName != null) {
      this.setUserName(userName);
    }
    if (userCode != null) {
      this.setUserCode(userCode);
    }
    if (userPassword != null) {
      this.setUserPassword(userPassword);
    }
    if (authority != null) {
      this.setAuthority(Authority.valueOf(authority));
    }
  }

  public enum Authority {
    ADMIN, USER
  }

}
