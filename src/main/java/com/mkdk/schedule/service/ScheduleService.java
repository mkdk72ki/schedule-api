package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.Group;
import com.mkdk.schedule.entity.Schedule;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.ScheduleMapper;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class ScheduleService {
  private final ScheduleMapper scheduleMapper;

  public ScheduleService(ScheduleMapper scheduleMapper) {
    this.scheduleMapper = scheduleMapper;
  }

  @PreAuthorize("hasAuthority('ADMIN')")
  public List<Schedule> findAll() {
    return scheduleMapper.findAll();
  }

  public List<Schedule> findSchedule(Integer groupId, LocalDate scheduleDate) {
    List<Schedule> getSchedule;
    if (Objects.isNull(groupId) && Objects.isNull(scheduleDate)) {
      getSchedule = scheduleMapper.findAll();
    } else if (Objects.nonNull(groupId) && Objects.nonNull(scheduleDate)) {
      getSchedule = scheduleMapper.findByGroupAndDate(groupId, scheduleDate);
    } else if (Objects.nonNull(groupId)) {
      getSchedule = scheduleMapper.findByGroup(groupId);
    } else {
      getSchedule = scheduleMapper.findByDate(scheduleDate);
    }
    return getSchedule;
  }

  public List<Schedule> checkSchedule(int userId, Integer groupId, LocalDate scheduleDate) {
    List<Schedule> getSchedule;
    if (Objects.isNull(groupId) && Objects.isNull(scheduleDate)) {
      scheduleMapper.intoGroupList(userId);
      getSchedule = scheduleMapper.checkSchedule();
    } else if (Objects.nonNull(groupId) && Objects.nonNull(scheduleDate)) {
      getSchedule = scheduleMapper.findByGroupAndDate(groupId, scheduleDate);
    } else if (Objects.nonNull(groupId)) {
      getSchedule = scheduleMapper.findByGroup(groupId);
    } else {
      getSchedule = scheduleMapper.findByDate(scheduleDate);
    }
    return getSchedule;
  }

  public Schedule findById(int scheduleId) {
    return scheduleMapper.findById(scheduleId)
        .orElseThrow(() -> new ResourceNotFoundException("schedule not found"));
  }

  public Map<String, Integer> getGroupMap() {
    List<Group> groups = scheduleMapper.findAllGroups();
    Map<String, Integer> groupMap = new LinkedHashMap<>();
    for (Group g : groups) {
      String groupName = g.getGroupName();
      Integer groupId = g.getGroupId();
      groupMap.put(groupName, groupId);
    }
    return groupMap;
  }

  public Map<String, Integer> getBelongingGroupMap(int userId) {
    List<Group> groups = scheduleMapper.belongingGroup(userId);
    Map<String, Integer> belongingGroupMap = new LinkedHashMap<>();
    for (Group g : groups) {
      String groupName = g.getGroupName();
      Integer groupId = g.getGroupId();
      belongingGroupMap.put(groupName, groupId);
    }
    return belongingGroupMap;
  }


  public Schedule createSchedule(String userCode, String groupName, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    int uId = scheduleMapper.findByUserCode(userCode);
    Map<String, Integer> groupMap = this.getGroupMap();
    String userId = String.valueOf(uId);
    String groupId = String.valueOf(groupMap.get(groupName));
    Schedule schedule = new Schedule(null, userId, groupId, title, scheduleDate, startTime, endTime, comment);
    scheduleMapper.create(schedule);
    return schedule;
  }

  public void updateSchedule(Integer scheduleId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    Schedule schedule = scheduleMapper.findById(scheduleId)
        .orElseThrow(() -> new ResourceNotFoundException("schedule not found"));
    schedule.update(title, scheduleDate, startTime, endTime, comment);
    this.scheduleMapper.update(schedule);
  }

  public void deleteSchedule(Integer scheduleId) {
    scheduleMapper.findById(scheduleId)
        .orElseThrow(() -> new ResourceNotFoundException("schedule not found"));
    scheduleMapper.delete(scheduleId);
  }

}
