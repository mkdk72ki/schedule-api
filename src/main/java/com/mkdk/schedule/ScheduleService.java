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

  public Schedule findById(int scheduleId) {
    return scheduleMapper.findById(scheduleId)
        .orElseThrow(() -> new ScheduleNotFoundException("schedule not found"));
  }

  public Schedule createSchedule(Integer userId, int groupId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    Schedule schedule = new Schedule(null, userId, groupId, title, scheduleDate, startTime, endTime, comment);
    scheduleMapper.create(schedule);
    return schedule;
  }

  public void updateSchedule(Integer scheduleId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    Schedule schedule = scheduleMapper.findById(scheduleId)
        .orElseThrow(() -> new ScheduleNotFoundException("schedule not found"));
    schedule.update(title, scheduleDate, startTime, endTime, comment);
    this.scheduleMapper.update(schedule);
  }

  public void deleteSchedule(Integer scheduleId) {
    scheduleMapper.findById(scheduleId)
            .orElseThrow(()->new ScheduleNotFoundException("schedule not found"));
    scheduleMapper.delete(scheduleId);
  }

}
