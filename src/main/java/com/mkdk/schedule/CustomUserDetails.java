package com.mkdk.schedule;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

public class CustomUserDetails extends User {
  public CustomUserDetails(String userCode, String userPassword, Collection<? extends GrantedAuthority> authorities) {
    super(userCode, userPassword, authorities);
  }

}
