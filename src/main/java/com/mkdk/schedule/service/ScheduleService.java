package com.mkdk.schedule.service;

import com.mkdk.schedule.entity.Schedule;
import com.mkdk.schedule.exception.ResourceNotFoundException;
import com.mkdk.schedule.mapper.ScheduleMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;

@Service
public class ScheduleService {
  private final ScheduleMapper scheduleMapper;

  public ScheduleService(ScheduleMapper scheduleMapper) {
    this.scheduleMapper = scheduleMapper;
  }

  public List<Schedule> findAll() {
    return scheduleMapper.findAll();
  }

  public List<Schedule> findSchedule(String groupName, LocalDate scheduleDate) {
    List<Schedule> getSchedule;
    if (Objects.isNull(groupName) && Objects.isNull(scheduleDate)) {
      getSchedule = scheduleMapper.findAll();
    } else if (Objects.nonNull(groupName) && Objects.isNull(scheduleDate)) {
      getSchedule = scheduleMapper.findByGroup(groupName);
    } else {
      getSchedule = scheduleMapper.findByDate(scheduleDate);
    }
    return getSchedule;
  }

  public Schedule findById(int scheduleId) {
    return scheduleMapper.findById(scheduleId)
        .orElseThrow(() -> new ResourceNotFoundException("schedule not found"));
  }

  public Schedule createSchedule(String userName, String groupName, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    int uId = scheduleMapper.findByUserName(userName);
    int gId = scheduleMapper.findByGroupName(groupName);
    String userId = String.valueOf(uId);
    String groupId = String.valueOf(gId);
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
