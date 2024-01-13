package com.mkdk.schedule;

import com.mkdk.schedule.mapper.UserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserMapper userMapper;

  public CustomUserDetailsService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public UserDetails loadUserByUsername(String userCode) throws UsernameNotFoundException {
    return userMapper.findByCode(userCode)
        .map(
            user -> new CustomUserDetails(
                user.getUserCode(),
                "{noop}" + user.getUserPassword(),
                Collections.emptyList()
            )
        )
        .orElseThrow(
            () -> new UsernameNotFoundException(
                "Given username is not found. (username = '" + userCode + "')"
            )
        );
  }
}
