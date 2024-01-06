package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private final UserMapper userMapper;

  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public List<User> findUsers() {
    List<User> getUsers = userMapper.findAll();
    return getUsers;
  }

  public User findById(int userId) {
    return userMapper.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found"));
  }

  public User createUser(String userName, String userPassword) {
    User user = new User(null, userName, userPassword);
    userMapper.create(user);
    return user;
  }

  public void updateUser(int userId, String userName, String userPassword) {
    User user = userMapper.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    user.update(userName, userPassword);
    userMapper.update(user);
  }

  public void deleteUser(int userId) {
    userMapper.findById(userId)
        .orElseThrow(() -> new ResourceNotFoundException("user not found"));
    userMapper.delete(userId);
  }

}
