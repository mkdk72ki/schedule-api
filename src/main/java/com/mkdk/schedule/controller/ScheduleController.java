package com.mkdk.schedule.controller;

import com.mkdk.schedule.controller.form.ScheduleCreateForm;
import com.mkdk.schedule.controller.form.ScheduleUpdateForm;
import com.mkdk.schedule.entity.Schedule;
import com.mkdk.schedule.service.ScheduleService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

@RestController
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @GetMapping("/schedule")
  public List<Schedule> findSchedule(@RequestParam(required = false) Integer groupId, LocalDate scheduleDate) {
    return scheduleService.findSchedule(groupId, scheduleDate);
  }

  @GetMapping("/schedule/{scheduleId}")
  public ResponseEntity<Schedule> findById(@PathVariable int scheduleId) {
    Schedule schedule = scheduleService.findById(scheduleId);
    return ResponseEntity.ok().body(schedule);
  }


  @PostMapping("/schedule")
  public ResponseEntity<MessageResponse> createSchedule(@RequestBody @Validated ScheduleCreateForm createForm, UriComponentsBuilder uriComponentsBuilder) {
    Schedule schedule = scheduleService.createSchedule(createForm.getUserId(), createForm.getGroupId(), createForm.getTitle(), createForm.getScheduleDate(), createForm.getStartTime(), createForm.getEndTime(), createForm.getComment());
    URI uri = uriComponentsBuilder.path("/schedule/{scheduleId}").buildAndExpand(schedule.getScheduleId()).toUri();
    MessageResponse body = new MessageResponse("登録しました");
    return ResponseEntity.created(uri).body(body);
  }

  @PatchMapping("/schedule/{scheduleId}")
  public ResponseEntity<MessageResponse> updateSchedule(@PathVariable int scheduleId, @RequestBody @Validated ScheduleUpdateForm updateForm) {
    scheduleService.updateSchedule(scheduleId, updateForm.getTitle(), updateForm.getScheduleDate(), updateForm.getStartTime(), updateForm.getEndTime(), updateForm.getComment());
    MessageResponse body = new MessageResponse("編集しました");
    return ResponseEntity.ok().body(body);
  }

  @DeleteMapping("/schedule/{scheduleId}")
  public ResponseEntity<MessageResponse> deleteSchedule(@PathVariable int scheduleId) {
    scheduleService.deleteSchedule(scheduleId);
    MessageResponse body = new MessageResponse("削除しました");
    return ResponseEntity.ok().body(body);
  }

}
