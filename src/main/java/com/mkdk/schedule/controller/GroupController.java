package com.mkdk.schedule.controller;

import com.mkdk.schedule.controller.form.GroupCreateForm;
import com.mkdk.schedule.controller.form.GroupOrder;
import com.mkdk.schedule.controller.form.GroupUpdateForm;
import com.mkdk.schedule.controller.form.UserCreateForm;
import com.mkdk.schedule.controller.form.UserUpdateForm;
import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.service.GroupService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
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
public class GroupController {

  private final GroupService groupService;

  public GroupController(GroupService groupService) {
    this.groupService = groupService;
  }

  @GetMapping("/groups")
  public List<Group> findUsers() {
    return groupService.findGroups();
  }

  @GetMapping("/groups/{groupId}")
  public ResponseEntity<Group> findById(@PathVariable int groupId) {
    Group group = groupService.findById(groupId);
    return ResponseEntity.ok().body(group);
  }

  @PostMapping("/groups")
  public ResponseEntity<MessageResponse> createGroup(@RequestBody @Validated GroupCreateForm form, UriComponentsBuilder uriComponentsBuilder) {
    Group group = groupService.createGroup(form.getGroupName(), form.getGroupPassword());
    URI uri = uriComponentsBuilder.path("/group/{groupId}").buildAndExpand(group.getGroupId()).toUri();
    MessageResponse body = new MessageResponse("登録しました");
    return ResponseEntity.created(uri).body(body);
  }

  @PatchMapping("/groups/{groupId}")
  public ResponseEntity<MessageResponse> updateGroup(@PathVariable int groupId, @RequestBody @Validated GroupUpdateForm form) {
    groupService.updateGroup(groupId, form.getGroupName(), form.getGroupPassword());
    MessageResponse body = new MessageResponse("編集しました");
    return ResponseEntity.ok().body(body);
  }

  @DeleteMapping("/groups/{groupId}")
  public ResponseEntity<MessageResponse> deleteGroup(@PathVariable int groupId) {
    groupService.deleteGroup(groupId);
    MessageResponse body = new MessageResponse("削除しました");
    return ResponseEntity.ok().body(body);
  }

}
