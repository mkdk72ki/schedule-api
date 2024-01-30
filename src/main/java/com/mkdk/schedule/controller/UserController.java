package com.mkdk.schedule.controller;

import com.mkdk.schedule.CustomUserDetails;
import com.mkdk.schedule.controller.form.UserCreateForm;
import com.mkdk.schedule.controller.form.UserUpdateForm;
import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/users/admin")
  public ModelAndView showList(ModelAndView modelAndView) {
    modelAndView.setViewName("/users/list");
    modelAndView.addObject("userList", userService.findAll());
    return modelAndView;
  }

  @GetMapping("/users")
  public ModelAndView showInfo(@AuthenticationPrincipal CustomUserDetails user, ModelAndView modelAndView) {
    modelAndView.setViewName("/users/info");
    modelAndView.addObject("userInfo", userService.findById(user.getUserId()));
    return modelAndView;
  }

  @GetMapping("/users/createForm")
  public ModelAndView showCreateForm(@ModelAttribute UserCreateForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("/users/createForm");
    modelAndView.addObject("createForm");
    return modelAndView;
  }

  @GetMapping("/users/updateForm/{userId}")
  public ModelAndView showUpdateForm(@PathVariable(value = "userId") int userId, @ModelAttribute UserUpdateForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("/users/updateForm");
    modelAndView.addObject("updateForm");
    return modelAndView;
  }

  @PostMapping("/users")
  public ModelAndView create(@Validated UserCreateForm form, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      return showCreateForm(form, modelAndView);
    }
    modelAndView.setViewName("redirect:/users/admin");
    modelAndView.addObject("create", userService.createUser(form.getUserName(), form.getUserCode(), form.getUserPassword(), form.getAuthority()));
    return modelAndView;
  }

  @PatchMapping("/users/{userId}")
  public ModelAndView update(@AuthenticationPrincipal User user, @PathVariable int userId, @Validated UserUpdateForm form, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      return showUpdateForm(userId, form, modelAndView);
    }
    modelAndView.setViewName("redirect:/users/admin");
    userService.updateUser(userService.findId(user.getUsername()), form.getUserName(), form.getUserCode(), form.getUserPassword(), form.getAuthority());
    return modelAndView;
  }

  @DeleteMapping("/users/{userId}")
  public ModelAndView delete(@PathVariable(value = "userId") int userId, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/users/admin");
    userService.deleteUser(userId);
    return modelAndView;
  }

  /*
  @PatchMapping("/users/edit")
  public ModelAndView update(@PathVariable int userId, @Validated UserUpdateForm form , BindingResult bindingResult, ModelAndView modelAndView){
    if (bindingResult.hasErrors()) {
      return showUpdateForm(form,modelAndView);
    }    modelAndView.setViewName("redirect:/users");
    modelAndView.addObject("update",userService.updateUser(userId, form.getUserName(), form.getUserCode(), form.getUserPassword()));
    return modelAndView;
  }

  @PatchMapping("/a/users/{userId}")
  public ResponseEntity<MessageResponse> updateUser(@PathVariable int userId, @RequestBody @Validated UserUpdateForm form) {
    userService.updateUser(userId, form.getUserName(), form.getUserCode(), form.getUserPassword());
    MessageResponse body = new MessageResponse("編集しました");
    return ResponseEntity.ok().body(body);
  }
*/

}
