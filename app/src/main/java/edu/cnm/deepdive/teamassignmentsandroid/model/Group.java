package edu.cnm.deepdive.teamassignmentsandroid.model;

import edu.cnm.deepdive.teamassignmentsandroid.model.entity.Task;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Group {

  private String name;
  private long groupId;
  private User owner;
  private Date creationDate;
  private List<Task> tasks;
  private Set<User> users;

  public String getName() {
    return name;
  }

  public long getGroupId() {
    return groupId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User getOwner() {
    return owner;
  }

  public void setOwner(User owner) {
    this.owner = owner;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public List<Task> getTasks() {
    return tasks;
  }

  public void setTasks(List<Task> tasks) {
    this.tasks = tasks;
  }

  public Set<User> getUsers() {
    return users;
  }

  public void setUsers(Set<User> users) {
    this.users = users;
  }
}
