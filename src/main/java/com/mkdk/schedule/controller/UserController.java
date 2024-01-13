package com.mkdk.schedule.controller;

import com.mkdk.schedule.controller.form.UserCreateForm;
import com.mkdk.schedule.controller.form.UserUpdateForm;
import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Collections;
import java.util.List;


@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users")
  public ModelAndView showList(ModelAndView modelAndView){
    modelAndView.setViewName("/users/list");
    modelAndView.addObject("userList", userService.findAll());
    return modelAndView;
  }

  @GetMapping("/users/createForm")
  public ModelAndView showCreateForm(@ModelAttribute UserCreateForm form, ModelAndView modelAndView){
    modelAndView.setViewName("/users/createForm");
    modelAndView.addObject("createForm");
    return modelAndView;
  }

  @GetMapping("/a/users")
  public List<User> findUsers() {
    return userService.findAll();
  }

  @GetMapping("/a/users/{userId}")
  public ResponseEntity<User> findById(@PathVariable int userId) {
    User user = userService.findById(userId);
    return ResponseEntity.ok().body(user);
  }

  @PostMapping("/users")
  public ModelAndView create(@Validated UserCreateForm form , BindingResult bindingResult, ModelAndView modelAndView){
    if (bindingResult.hasErrors()) {
      return showCreateForm(form,modelAndView);
    }    modelAndView.setViewName("redirect:/users");
    modelAndView.addObject("create",userService.createUser(form.getUserName(), form.getUserCode(), form.getUserPassword()));
    return modelAndView;
  }

  @PostMapping("/a/users")
  public ResponseEntity<MessageResponse> createUser(@RequestBody @Validated UserCreateForm form, UriComponentsBuilder uriComponentsBuilder) {
    User user = userService.createUser(form.getUserName(), form.getUserCode(), form.getUserPassword());
    URI uri = uriComponentsBuilder.path("/users/{userId}").buildAndExpand(user.getUserId()).toUri();
    MessageResponse body = new MessageResponse("登録しました");
    return ResponseEntity.created(uri).body(body);
  }

  @PatchMapping("/a/users/{userId}")
  public ResponseEntity<MessageResponse> updateUser(@PathVariable int userId, @RequestBody @Validated UserUpdateForm form) {
    userService.updateUser(userId, form.getUserName(), form.getUserCode(), form.getUserPassword());
    MessageResponse body = new MessageResponse("編集しました");
    return ResponseEntity.ok().body(body);
  }

  @DeleteMapping("/a/users/{userId}")
  public ResponseEntity<MessageResponse> deleteUser(@PathVariable int userId) {
    userService.deleteUser(userId);
    MessageResponse body = new MessageResponse("削除しました");
    return ResponseEntity.ok().body(body);
  }

}
