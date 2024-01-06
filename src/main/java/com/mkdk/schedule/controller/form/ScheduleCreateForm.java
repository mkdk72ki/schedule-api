package com.mkdk.schedule.controller.form;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;

public class ScheduleCreateForm {

  @NotNull
  private int userId;

  @NotNull(message = "選択してください")
  private int groupId;

  @NotBlank(message = "入力してください")
  @Length(max = 20, message = "20字以内で入力してください")
  private String title;

  @NotNull(message = "入力してください")
  @DateTimeFormat(pattern = "yyyy-mm-dd")
  private LocalDate scheduleDate;

  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime startTime;

  @DateTimeFormat(pattern = "HH:mm")
  private LocalTime endTime;

  @Max(value = 100, message = "100字以内で入力してください")
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
