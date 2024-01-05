package com.mkdk.schedule.controller;

import com.mkdk.schedule.MessageResponse;
import com.mkdk.schedule.Schedule;
import com.mkdk.schedule.ScheduleCreateForm;
import com.mkdk.schedule.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
  public ResponseEntity<MessageResponse> createUser(@RequestBody ScheduleCreateForm CreateForm, UriComponentsBuilder uriComponentsBuilder) {
    Schedule schedule = scheduleService.createSchedule(CreateForm.getUserId(), CreateForm.getGroupId(), CreateForm.getTitle(), CreateForm.getScheduleDate(), CreateForm.getStartTime(), CreateForm.getEndTime(), CreateForm.getComment());
    URI uri = uriComponentsBuilder.path("/schedule/{id}").buildAndExpand(schedule.getScheduleId()).toUri();
    MessageResponse body = new MessageResponse("登録しました");
    return ResponseEntity.created(uri).body(body);
  }

}
