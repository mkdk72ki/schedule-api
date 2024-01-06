package com.mkdk.schedule.controller;

import com.mkdk.schedule.controller.form.UserCreateForm;
import com.mkdk.schedule.controller.form.UserUpdateForm;
import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
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

  @PostMapping("/users")
  public ResponseEntity<MessageResponse> createUser(@RequestBody UserCreateForm form, UriComponentsBuilder uriComponentsBuilder){
    User user = userService.createUser(form.getUserName(), form.getUserPassword());
    URI uri = uriComponentsBuilder.path("/users/{userId}").buildAndExpand(user.getUserId()).toUri();
    MessageResponse body = new MessageResponse("登録しました");
    return ResponseEntity.created(uri).body(body);
  }
  @PatchMapping("/users/{userId}")
  public ResponseEntity<MessageResponse> updateUser(@PathVariable int userId, @RequestBody UserUpdateForm form){
    userService.updateUser(userId, form.getUserName(), form.getUserPassword());
    MessageResponse body = new MessageResponse("編集しました");
    return ResponseEntity.ok().body(body);
  }

  @DeleteMapping("/users/{userId}")
  public ResponseEntity<MessageResponse> deleteUser(@PathVariable int userId){
    userService.deleteUser(userId);
    MessageResponse body = new MessageResponse("削除しました");
    return ResponseEntity.ok().body(body);
  }

}
