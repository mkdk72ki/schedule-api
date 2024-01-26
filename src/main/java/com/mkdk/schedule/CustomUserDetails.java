package com.mkdk.schedule;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
  private final Integer userId;

  public CustomUserDetails(String userCode, String userPassword, Collection<? extends GrantedAuthority> authorities, Integer userId) {
    super(userCode, userPassword, authorities);
    this.userId = userId;
  }


  public Integer getUserId() {
    return userId;
  }
}
