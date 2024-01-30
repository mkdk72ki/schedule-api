package com.mkdk.schedule.entity;

public class Belonging {
  private int userId;

  private int groupId;

  public Belonging(int userId, int getUserId) {
    this.userId = userId;
    this.groupId = getUserId;
  }

  public int getUserId() {
    return userId;
  }

  public void setUserId(int userId) {
    this.userId = userId;
  }

  public int getGroupId() {
    return groupId;
  }

  public void setGroupId(int groupId) {
    this.groupId = groupId;
  }
}
