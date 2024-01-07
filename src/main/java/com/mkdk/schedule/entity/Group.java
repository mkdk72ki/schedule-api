package com.mkdk.schedule.entity;

public class Group {

  private Integer groupId;

  private String groupName;

  private String groupPassword;

  public Group(Integer groupId, String groupName, String groupPassword) {
    this.groupId = groupId;
    this.groupName = groupName;
    this.groupPassword = groupPassword;
  }

  public Integer getGroupId() {
    return groupId;
  }

  public void setGroupId(Integer groupId) {
    this.groupId = groupId;
  }

  public String getGroupName() {
    return groupName;
  }

  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }

  public String getGroupPassword() {
    return groupPassword;
  }

  public void setGroupPassword(String groupPassword) {
    this.groupPassword = groupPassword;
  }

  public void update(String groupName, String groupPassword) {
    if (groupName != null) {
      this.setGroupName(groupName);
    }
    if (groupPassword != null) {
      this.setGroupPassword(groupPassword);
    }
  }

}
