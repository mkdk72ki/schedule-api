package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.exception.ResourceExistsException;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.UserMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService {
  private final UserMapper userMapper;
  private final PasswordEncoder passwordEncoder;

  public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
    this.userMapper = userMapper;
    this.passwordEncoder = passwordEncoder;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  public List<User> findAll() {
    List<User> getUsers = userMapper.findAll();
    return getUsers;
  }

  public User findById(int userId) {
    return userMapper.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found"));
  }

  public Integer findId(String userCode){
    return userMapper.findIdByCode(userCode);
  }

  public User createUser(String userName, String userCode, String userPassword, String authority) {
    var encodedPassword = passwordEncoder.encode(userPassword);
    User user = new User(null, userName, userCode, encodedPassword, User.Authority.valueOf(authority));
    if (userMapper.findByCode(user.getUserCode()).isPresent()) {
      throw new ResourceExistsException("code already exists");
    } else {
      userMapper.create(user);
      return user;
    }
  }

  public void updateUser(int userId, String userName, String userCode, String userPassword, String authority) {
    User user = userMapper.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    var encodedPassword = passwordEncoder.encode(userPassword);
    user.update(userName, userCode, encodedPassword,authority);
    userMapper.update(user);
  }

  public void deleteUser(int userId) {
    userMapper.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    userMapper.delete(userId);
  }

}
