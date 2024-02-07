package com.mkdk.schedule.validation;

import com.mkdk.schedule.CustomUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class SecuritySession {

  public Integer getUserId() {
    Authentication authentication = SecurityContextHolder.getContext()
        .getAuthentication();
    if (authentication != null) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof CustomUserDetails) {
        return ( (CustomUserDetails) principal ).getUserId();
      }
    }
    return null;
  }
}
