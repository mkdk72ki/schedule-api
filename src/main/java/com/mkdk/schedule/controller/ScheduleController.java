package com.mkdk.schedule.controller;

import com.mkdk.schedule.CustomUserDetails;
import com.mkdk.schedule.controller.form.ScheduleCreateForm;
import com.mkdk.schedule.controller.form.ScheduleUpdateForm;
import com.mkdk.schedule.service.ScheduleService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.Map;

@RestController
public class ScheduleController {

  private final ScheduleService scheduleService;

  public ScheduleController(ScheduleService scheduleService) {
    this.scheduleService = scheduleService;
  }

  @GetMapping("/schedule/admin")
  public ModelAndView showSchedule(@RequestParam(required = false) Integer groupId, LocalDate scheduleDate, ModelAndView modelAndView) {
    Map<String, Integer> groupMap = scheduleService.getGroupMap();
    modelAndView.addObject("groupMap", groupMap);
    modelAndView.setViewName("/schedule/list-all");
    modelAndView.addObject("scheduleList", scheduleService.findSchedule(groupId, scheduleDate));
    return modelAndView;
  }

  @GetMapping("/schedule")
  public ModelAndView checkSchedule(@RequestParam(required = false) Integer groupId, LocalDate scheduleDate, @AuthenticationPrincipal CustomUserDetails user, ModelAndView modelAndView) {
    Map<String, Integer> groupMap = scheduleService.getBelongingGroupMap(user.getUserId());
    modelAndView.addObject("belongingGroupMap", groupMap);
    modelAndView.setViewName("/schedule/list");
    modelAndView.addObject("scheduleList", scheduleService.checkSchedule(user.getUserId(), groupId, scheduleDate));
    return modelAndView;
  }

  @GetMapping("/schedule/createForm")
  public ModelAndView showCreateForm(@AuthenticationPrincipal CustomUserDetails user, @ModelAttribute ScheduleCreateForm form, ModelAndView modelAndView) {
    Map<String, Integer> groupMap = scheduleService.getBelongingGroupMap(user.getUserId());
    modelAndView.addObject("belongingGroupMap", groupMap);
    modelAndView.setViewName("/schedule/createForm");
    modelAndView.addObject("createForm");
    return modelAndView;
  }

  @PostMapping("/schedule")
  public ModelAndView create(@AuthenticationPrincipal CustomUserDetails user, @Validated ScheduleCreateForm form, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      return showCreateForm(user, form, modelAndView);
    }
    modelAndView.setViewName("redirect:/schedule");
    modelAndView.addObject("create", scheduleService.createSchedule(user.getUsername(), form.getGroupName(), form.getTitle(), form.getScheduleDate(), form.getStartTime(), form.getEndTime(), form.getComment()));
    return modelAndView;
  }

  @GetMapping("/schedule/edit/{scheduleId}")
  public ModelAndView showUpdateForm(@PathVariable(value = "scheduleId") int scheduleId, @AuthenticationPrincipal CustomUserDetails user, @ModelAttribute ScheduleUpdateForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("/schedule/updateForm");
    modelAndView.addObject("scheduleList", scheduleService.checkSchedule(user.getUserId(), null, null));
    modelAndView.addObject("scheduleInfo", scheduleService.findById(scheduleId));
    modelAndView.addObject("belongingGroupMap", scheduleService.getGroupMap());
    modelAndView.addObject("scheduleUpdateForm");
    return modelAndView;
  }

  @PatchMapping("/schedule/edit/{scheduleId}")
  public ModelAndView update(@PathVariable(value = "scheduleId") int scheduleId, @AuthenticationPrincipal CustomUserDetails user, @Validated ScheduleUpdateForm form, BindingResult bindingResult, ModelAndView modelAndView) {
    if (bindingResult.hasErrors()) {
      return showUpdateForm(scheduleId, user, form, modelAndView);
    }
    modelAndView.setViewName("redirect:/schedule");
    scheduleService.updateSchedule(scheduleId, form.getTitle(), form.getScheduleDate(), form.getStartTime(), form.getEndTime(), form.getComment());
    return modelAndView;
  }

  @DeleteMapping("/schedule/{scheduleId}")
  public ModelAndView delete(@PathVariable(value = "scheduleId") int scheduleId, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/schedule");
    scheduleService.deleteSchedule(scheduleId);
    return modelAndView;
  }
}
