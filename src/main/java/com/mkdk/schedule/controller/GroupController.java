package com.mkdk.schedule.controller;

import com.mkdk.schedule.CustomUserDetails;
import com.mkdk.schedule.controller.form.BelongingForm;
import com.mkdk.schedule.controller.form.GroupCreateForm;
import com.mkdk.schedule.controller.form.GroupUpdateForm;
import com.mkdk.schedule.controller.form.UserUpdateForm;
import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

@RestController
public class GroupController {

  private final GroupService groupService;

  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @GetMapping("/groups/admin")
  public ModelAndView showList(ModelAndView modelAndView) {
    modelAndView.setViewName("/groups/list");
    modelAndView.addObject("groupList", groupService.findAll());
    return modelAndView;
  }

  @GetMapping("/groups/userList/{groupId}")
  public ModelAndView showUserList(@PathVariable int groupId, ModelAndView modelAndView) {
    modelAndView.setViewName("/groups/userList");
    modelAndView.addObject("groupUserList", groupService.findBelongingUser(groupId));
    modelAndView.addObject("group", groupService.findById(groupId));
    return modelAndView;
  }

  @GetMapping("/groups")
  public ModelAndView showGroupList(@AuthenticationPrincipal CustomUserDetails user, ModelAndView modelAndView) {
    modelAndView.setViewName("/users/groupList");
    modelAndView.addObject("userGroupList", groupService.findBelongingGroups(user.getUserId()));
    return modelAndView;
  }

  @GetMapping("/groups/createForm")
  public ModelAndView showCreateForm(@ModelAttribute GroupCreateForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("/groups/createForm");
    modelAndView.addObject("createForm");
    return modelAndView;
  }

  @PostMapping("/groups")
  public ModelAndView create(@AuthenticationPrincipal CustomUserDetails user, @Validated GroupCreateForm form, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      return showCreateForm(form, modelAndView);
    }
    modelAndView.setViewName("redirect:/groups");
    modelAndView.addObject("create", groupService.createGroup(user.getUserId(), form.getGroupName(), form.getGroupCode(), form.getGroupPassword()));
    return modelAndView;
  }

  @GetMapping("/groups/edit/{groupId}")
  public ModelAndView showUpdateForm(@PathVariable(value = "groupId") int groupId, @ModelAttribute GroupUpdateForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("/groups/updateForm");
    modelAndView.addObject("groupInfo", groupService.findById(groupId));
    modelAndView.addObject("groupUpdateForm");
    return modelAndView;
  }

  @PatchMapping("/groups/edit/{groupId}")
  public ModelAndView update(@PathVariable(value = "groupId") int groupId, @Validated GroupUpdateForm form, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      return showUpdateForm(groupId, form, modelAndView);
    }
    modelAndView.setViewName("redirect:/groups");
    groupService.updateGroup(groupId, form.getGroupName(), form.getGroupCode(), form.getGroupPassword());
    return modelAndView;
  }

  @DeleteMapping("/groups/{groupId}")
  public ModelAndView delete(@PathVariable(value = "groupId") int groupId, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/groups");
    groupService.deleteGroup(groupId);
    return modelAndView;
  }

  // belonging

  @GetMapping("/groups/belongingForm")
  public ModelAndView showBelongingForm(@ModelAttribute BelongingForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("/groups/belongingForm");
    modelAndView.addObject("belongingForm");
    return modelAndView;
  }

  @PostMapping("/groups/belonging")
  public ModelAndView belong(@AuthenticationPrincipal CustomUserDetails user, BelongingForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/groups");
    groupService.belongGroup(user.getUserId(), form.getGroupCode(), form.getGroupPassword());
    return modelAndView;
  }

  @DeleteMapping("/groups/belonging/{groupId}")
  public ModelAndView leave(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "groupId") int groupId, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/groups");
    groupService.leaveGroup(user.getUserId(), groupId);
    return modelAndView;
  }

}
