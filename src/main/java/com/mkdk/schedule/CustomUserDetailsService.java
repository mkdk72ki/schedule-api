package com.mkdk.schedule;

import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

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
                user.getUserPassword(),
                toGrantedAuthorityList(user.getAuthority()),
                user.getUserId()
            )
        )
        .orElseThrow(
            () -> new UsernameNotFoundException(
                "Given username is not found. (username = '" + userCode + "')"
            )
        );
  }

  private List<GrantedAuthority> toGrantedAuthorityList(User.Authority authority) {
    return Collections.singletonList(new SimpleGrantedAuthority(authority.name()));
  }

}
