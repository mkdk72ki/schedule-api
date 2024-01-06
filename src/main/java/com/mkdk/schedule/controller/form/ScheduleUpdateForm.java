package com.mkdk.schedule.controller.form;

import jakarta.validation.constraints.Max;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleUpdateForm {

  @Max(value = 20, message = "20字以内で入力してください")
  private String title;

  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate scheduleDate;

  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime startTime;

  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime endTime;

  @Max(value = 100, message = "100字以内で入力してください")
  private String comment;

  public ScheduleUpdateForm(String title, LocalDate scheduleDate, LocalTime startTime, LocalTime endTime, String comment) {
    this.title = title;
    this.scheduleDate = scheduleDate;
    this.startTime = startTime;
    this.endTime = endTime;
    this.comment = comment;
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
