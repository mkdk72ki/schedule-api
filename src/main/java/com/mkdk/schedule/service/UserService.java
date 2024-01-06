package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
  private final UserMapper userMapper;

  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public List<User> findUsers(){
    List<User> getUsers = userMapper.findAll();
    return getUsers;
  }
}
