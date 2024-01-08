package com.mkdk.schedule.entity;

public class Group {

  private Integer groupId;

  private String groupName;

  private String groupCode;

  private String groupPassword;

  public Group(Integer groupId, String groupName, String groupCode, String groupPassword) {
    this.groupId = groupId;
    this.groupName = groupName;
    this.groupCode = groupCode;
    this.groupPassword = groupPassword;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupCode() {
    return groupCode;
  }

  public void setGroupCode(String groupCode) {
    this.groupCode = groupCode;
  }

  public String getGroupPassword() {
    return groupPassword;
  }

  public void setGroupPassword(String groupPassword) {
    this.groupPassword = groupPassword;
  }

  public void update(String groupName, String groupCode, String groupPassword) {
    if (groupName != null) {
      this.setGroupName(groupName);
    }
    if (groupCode != null) {
      this.setGroupCode(groupCode);
    }
    if (groupPassword != null) {
      this.setGroupPassword(groupPassword);
    }
  }

}
