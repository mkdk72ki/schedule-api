package com.mkdk.schedule;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleCreateForm {

  private int userId;

  private int groupId;

  private String title;


  private LocalDate scheduleDate;

  private LocalTime startTime;

  private LocalTime endTime;

  private String comment;

  public ScheduleCreateForm(int userId, int groupId, String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    this.userId = userId;
    this.groupId = groupId;
    this.title = title;
    this.scheduleDate = scheduleDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.comment = comment;
  }

  public int getUserId() {
    return userId;
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
}
