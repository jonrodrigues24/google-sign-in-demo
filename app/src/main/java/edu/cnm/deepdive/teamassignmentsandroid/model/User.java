package edu.cnm.deepdive.teamassignmentsandroid.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class User {

  private Long id;
  private Date connected;
  private String displayName;
  private Date creationDate;
  private String oauthKey;
  private final List<Group> groups = new LinkedList<>();
  private final List<Task> tasks = new LinkedList<>();

  public Long getId() {
    return id;
  }

  public Date getConnected() {
    return connected;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  public List<Group> getGroups() {
    return groups;
  }

  public List<Task> getTasks() {
    return tasks;
  }
}
