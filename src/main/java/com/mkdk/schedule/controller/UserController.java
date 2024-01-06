package com.mkdk.schedule.controller;

import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public List<User> findUsers(){
    return userService.findUsers();
  }

}
