package com.mkdk.schedule.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Schedule {

  private final Integer scheduleId;

  private String userName;

  private String groupName;

  private String title;

  private LocalDate scheduleDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private String comment;

  public Schedule(Integer scheduleId, String userName, String groupName, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    this.scheduleId = scheduleId;
    this.userName = userName;
    this.groupName = groupName;
    this.title = title;
    this.scheduleDate = scheduleDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.comment = comment;
  }

  public Integer getScheduleId() {
    return scheduleId;
  }

  public String getUserName() {
    return userName;
  }

  public void setUserName(String userName) {
    this.userName = userName;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
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

  public void update(String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {

    if (title != null) {
      this.setTitle(title);
    }

    if (scheduleDate != null) {
      this.setScheduleDate(scheduleDate);
    }

    if (startTime != null) {
      this.setStartTime(startTime);
    }

    if (endTime != null) {
      this.setEndTime(endTime);
    }

    if (comment != null) {
      this.setComment(comment);
    }

  }

}
