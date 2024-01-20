package com.mkdk.schedule.controller;

import com.mkdk.schedule.controller.form.ScheduleCreateForm;
import com.mkdk.schedule.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestController
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @GetMapping("/schedule")
  public ModelAndView showList(ModelAndView modelAndView) {
    modelAndView.setViewName("/schedule/list");
    modelAndView.addObject("scheduleList", scheduleService.findAll());
    return modelAndView;
  }

  @GetMapping("/schedule/createForm")
  public ModelAndView showCreateForm(@ModelAttribute ScheduleCreateForm form, ModelAndView modelAndView) {
    Map<String, Integer> groupMap = scheduleService.getGroupMap();
    modelAndView.addObject("groupMap", groupMap);
    modelAndView.setViewName("/schedule/createForm");
    modelAndView.addObject("createForm");
    return modelAndView;
  }

  @PostMapping("/schedule")
  public ModelAndView create(@AuthenticationPrincipal User user, @Validated ScheduleCreateForm form, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      return showCreateForm(form, modelAndView);
    }
    modelAndView.setViewName("redirect:/schedule");
    modelAndView.addObject("create", scheduleService.createSchedule(user.getUsername(), form.getGroupName(), form.getTitle(), form.getScheduleDate(), form.getStartTime(), form.getEndTime(), form.getComment()));
    return modelAndView;
  }

  @DeleteMapping("/schedule/{scheduleId}")
  public ModelAndView delete(@PathVariable(value = "scheduleId") int scheduleId, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/schedule");
    scheduleService.deleteSchedule(scheduleId);
    return modelAndView;
  }

  /*
  @GetMapping("/a/schedule")
  public List<Schedule> findSchedule(@RequestParam(required = false) String groupName, LocalDate scheduleDate) {
    return scheduleService.findSchedule(groupName, scheduleDate);
  }

  @PatchMapping("/schedule/{scheduleId}")
  public ResponseEntity<MessageResponse> updateSchedule(@PathVariable int scheduleId, @RequestBody @Validated ScheduleUpdateForm updateForm) {
    scheduleService.updateSchedule(scheduleId, updateForm.getTitle(), updateForm.getScheduleDate(), updateForm.getStartTime(), updateForm.getEndTime(), updateForm.getComment());
    MessageResponse body = new MessageResponse("編集しました");
    return ResponseEntity.ok().body(body);
  }
   */

}
