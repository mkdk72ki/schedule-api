package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.Belonging;
import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.entity.User;
import com.mkdk.schedule.exception.PasswordException;
import com.mkdk.schedule.exception.ResourceExistsException;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.GroupMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

  public Group createGroup(int userId,String groupName, String groupCode, String groupPassword) {
    Group group = new Group(null, groupName, groupCode, groupPassword);
    if (groupMapper.findByCode(group.getGroupCode()).isPresent()) {
      throw new ResourceExistsException("code already exists");
    } else {
      groupMapper.create(group);
      Belonging join = new Belonging(userId, group.getGroupId());
      groupMapper.belong(join);
      return group;
    }
  }

  public void updateGroup(int groupId, String groupName, String groupCode, String groupPassword) {
    Group group = groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    if (!Objects.equals(groupCode,groupMapper.findCode(groupId)) && groupMapper.findByCode(groupCode).isPresent()) {
      throw new ResourceExistsException("code already exists");
    }
      group.update(groupName, groupCode, groupPassword);
    groupMapper.update(group);
  }

  public void deleteGroup(int groupId) {
    groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    groupMapper.delete(groupId);
  }

  // belonging

  public void belongGroup(int userId, String groupCode, String groupPassword) {
    Optional<Group> group = groupMapper.findGroup(groupCode, groupPassword);
    if (groupMapper.findByCode(groupCode).isEmpty()) {
      throw new ResourceNotFoundException("group not found");
    } else if (group.isEmpty()) {
      throw new PasswordException("password doesn't matched");
    } else if (groupMapper.findGroupId(userId, group.get().getGroupId()).isPresent()) {
      throw new ResourceExistsException("already belong to the group");
    } else {
      Belonging join = new Belonging(userId, group.get().getGroupId());
      groupMapper.belong(join);
    }
  }

  public void leaveGroup(int userId, int groupId) {
    groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    groupMapper.leave(userId, groupId);
  }

}
