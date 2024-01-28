package com.mkdk.schedule.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BelongingForm {
  @NotBlank
  private String groupCode;

  @NotBlank
  private String groupPassword;

  public BelongingForm(String groupCode, String groupPassword) {
    this.groupCode = groupCode;
    this.groupPassword = groupPassword;
  }

  public String getGroupCode() {
    return groupCode;
  }

  public String getGroupPassword() {
    return groupPassword;
  }
}
