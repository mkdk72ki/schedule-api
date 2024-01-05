package com.mkdk.schedule.controller;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleUpdateForm {

  private int groupId;

  private String title;


  private LocalDate scheduleDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private String comment;

  public ScheduleUpdateForm(int groupId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    this.groupId = groupId;
    this.title = title;
    this.scheduleDate = scheduleDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.comment = comment;
  }

  public int getGroupId() {
    return groupId;
  }

  public String getTitle() {
    return title;
  }

  public LocalDate getScheduleDate() {
    return scheduleDate;
  }

  public LocalTime getStartTime() {
    return startTime;
  }

  public LocalTime getEndTime() {
    return endTime;
  }

  public String getComment() {
    return comment;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setScheduleDate(LocalDate scheduleDate) {
    this.scheduleDate = scheduleDate;
  }

  public void setStartTime(LocalTime startTime) {
    this.startTime = startTime;
  }

  public void setEndTime(LocalTime endTime) {
    this.endTime = endTime;
  }

  public void setComment(String comment) {
    this.comment = comment;
  }
}
