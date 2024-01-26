package com.mkdk.schedule.controller;

import com.mkdk.schedule.CustomUserDetails;
import com.mkdk.schedule.controller.form.BelongingForm;
import com.mkdk.schedule.service.BelongingService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class BelongingController {
  private final BelongingService belongingService;

  public BelongingController(BelongingService belongingService) {
    this.belongingService = belongingService;
  }

  @GetMapping("/groups/belongingForm")
  public ModelAndView showBelongingForm(@ModelAttribute BelongingForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("/groups/belongingForm");
    modelAndView.addObject("belongingForm");
    return modelAndView;
  }

  @PostMapping("/groups/belonging")
  public ModelAndView belong(@AuthenticationPrincipal CustomUserDetails user, BelongingForm form, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/groups");
    belongingService.belongGroup(user.getUserId(), form.getGroupCode(), form.getGroupPassword());
    return modelAndView;
  }

  @DeleteMapping("/groups/belonging/{groupId}")
  public ModelAndView leave(@AuthenticationPrincipal CustomUserDetails user, @PathVariable(value = "groupId") int groupId, ModelAndView modelAndView) {
    modelAndView.setViewName("redirect:/groups");
    belongingService.leaveGroup(user.getUserId(), groupId);
    return modelAndView;
  }

}
