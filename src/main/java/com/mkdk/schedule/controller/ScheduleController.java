package com.mkdk.schedule.controller;

import com.mkdk.schedule.MessageResponse;
import com.mkdk.schedule.Schedule;
import com.mkdk.schedule.ScheduleCreateForm;
import com.mkdk.schedule.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @GetMapping("/schedule")
  public List<Schedule> findSchedule() {
    List<Schedule> schedules = scheduleService.findSchedule();
    return schedules;
  }

  @PostMapping("/schedule")
  public ResponseEntity<MessageResponse> createSchedule(@RequestBody ScheduleCreateForm createForm, UriComponentsBuilder uriComponentsBuilder) {
    Schedule schedule = scheduleService.createSchedule(createForm.getUserId(), createForm.getGroupId(), createForm.getTitle(), createForm.getScheduleDate(), createForm.getStartTime(), createForm.getEndTime(), createForm.getComment());
    URI uri = uriComponentsBuilder.path("/schedule/{scheduleId}").buildAndExpand(schedule.getScheduleId()).toUri();
    MessageResponse body = new MessageResponse("登録しました");
    return ResponseEntity.created(uri).body(body);
  }

  @PatchMapping("/schedule/{scheduleId}")
  public ResponseEntity<MessageResponse> updateSchedule(@RequestBody ScheduleUpdateForm updateForm) {
    scheduleService.updateSchedule(updateForm.getGroupId(), updateForm.getTitle(), updateForm.getScheduleDate(), updateForm.getStartTime(), updateForm.getEndTime(), updateForm.getComment());
    MessageResponse body = new MessageResponse("編集しました");
    return ResponseEntity.ok().body(body);
  }

}
