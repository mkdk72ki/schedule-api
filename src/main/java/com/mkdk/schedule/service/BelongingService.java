package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.Belonging;
import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.BelongingMapper;
import com.mkdk.schedule.mapper.GroupMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BelongingService {
  private final BelongingMapper belongingMapper;
  private final GroupMapper groupMapper;

  public BelongingService(BelongingMapper belongingMapper, GroupMapper groupMapper) {
    this.belongingMapper = belongingMapper;
    this.groupMapper = groupMapper;
  }

  public void belongGroup(int userId, String groupCode, String groupPassword) {
    Optional<Group> group = groupMapper.findGroup(groupCode, groupPassword);
    if (groupMapper.findByCode(groupCode).isEmpty()) {
      throw new ResourceNotFoundException("group not found");
    } else if (group.isPresent()) {
      Integer groupId = group.get().getGroupId();
      Belonging belonging = new Belonging(userId, groupId);
      belongingMapper.belong(belonging);
    }
  }

  public void leaveGroup(int userId, int groupId) {
    groupMapper.findById(groupId)
        .orElseThrow(() -> new ResourceNotFoundException("group not found"));
    belongingMapper.leave(userId, groupId);
  }

}
