package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.exception.ResourceExistsException;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.GroupMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

  private final GroupMapper groupMapper;

  public GroupService(GroupMapper groupMapper) {
    this.groupMapper = groupMapper;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  public List<Group> findAll() {
    List<Group> getGroups = groupMapper.findAll();
    return getGroups;
  }

  public List<User> findBelongingUser(int groupId) {
    List<User> users = groupMapper.belongingUser(groupId);
    return users;
  }

  public List<Group> findBelongingGroups(Integer userId) {
    List<Group> groupName = groupMapper.belongingGroup(userId);
    return groupName;
  }

  public Group findById(int groupId) {
    return groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
  }

  public Group createGroup(String groupName, String groupCode, String groupPassword) {
    Group group = new Group(null, groupName, groupCode, groupPassword);
    if (groupMapper.findByCode(group.getGroupCode()).isPresent()) {
      throw new ResourceExistsException("code already exists");
    } else {
      groupMapper.create(group);
      return group;
    }
  }

  public void updateGroup(int groupId, String groupName, String groupCode, String groupPassword) {
    Group group = groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    group.update(groupName, groupCode, groupPassword);
    groupMapper.update(group);
  }

  public void deleteGroup(int groupId) {
    groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    groupMapper.delete(groupId);
  }

}
