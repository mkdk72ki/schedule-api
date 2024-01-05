package com.mkdk.schedule;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Service
public class ScheduleService {
  private final ScheduleMapper scheduleMapper;

  public ScheduleService(ScheduleMapper scheduleMapper) {
    this.scheduleMapper = scheduleMapper;
  }

  public List<Schedule> findSchedule() {
    List<Schedule> getSchedule;
    getSchedule = scheduleMapper.findAll();
    return getSchedule;
  }

  public Schedule createSchedule(int userId, int groupId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    Schedule schedule = new Schedule(null, userId, groupId, title, scheduleDate, startTime, endTime, comment);
    scheduleMapper.create(schedule);
    return schedule;
  }

  public void updateSchedule(int groupId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    Schedule schedule = new Schedule(null, null, groupId, title, scheduleDate, startTime, endTime, comment);
    scheduleMapper.update(schedule);
  }

  public void deleteSchedule(int scheduleId) {
    scheduleMapper.delete(scheduleId);
  }

}
