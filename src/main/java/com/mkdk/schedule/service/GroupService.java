package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.GroupMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {

  private final GroupMapper groupMapper;

  public GroupService(GroupMapper groupMapper) {
    this.groupMapper = groupMapper;
  }

  public List<Group> findGroups() {
    List<Group> getGroups = groupMapper.findAll();
    return getGroups;
  }

  public Group findById(int groupId) {
    return groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
  }

  public Group createGroup(String groupName, String groupPassword) {
    Group group = new Group(null, groupName, groupPassword);
    groupMapper.create(group);
    return group;
  }

  public void updateGroup(int groupId, String groupName, String groupPassword) {
    Group group = groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    group.update(groupName, groupPassword);
    groupMapper.update(group);
  }

  public void deleteGroup(int groupId) {
    groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    groupMapper.delete(groupId);
  }

}
