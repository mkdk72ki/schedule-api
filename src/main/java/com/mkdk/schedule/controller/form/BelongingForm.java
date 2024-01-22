package com.mkdk.schedule.controller.form;

public class BelongingForm {
  private String groupCode;
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
