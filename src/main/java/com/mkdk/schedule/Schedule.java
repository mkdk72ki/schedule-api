package com.mkdk.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {

  private int scheduleId;

  private int userId;

  private int groupId;

  private String title;

  private LocalDate scheduleDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private String comment;

  public Schedule(Integer scheduleId, Integer userId, int groupId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    this.scheduleId = scheduleId;
    this.userId = userId;
    this.groupId = groupId;
    this.title = title;
    this.scheduleDate = scheduleDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.comment = comment;
  }

  public int getScheduleId() {
    return scheduleId;
  }

  public void setScheduleId(int scheduleId) {
    this.scheduleId = scheduleId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public LocalDate getScheduleDate() {
    return scheduleDate;
  }

  public void setScheduleDate(LocalDate scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public String getComment() {
    return comment;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
