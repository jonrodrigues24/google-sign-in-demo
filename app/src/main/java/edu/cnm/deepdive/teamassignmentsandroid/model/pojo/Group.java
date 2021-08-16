package edu.cnm.deepdive.teamassignmentsandroid.model.pojo;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.Task;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class Group {
@Expose
  private String name;
@Expose
  private long id;
  @Expose
  private User owner;
  @Expose
  private Date creationDate;
  @Expose
  private List<Task> tasks;
  @Expose
  private Set<User> users;

  public String getName() {
    return name;
  }

  public long getId() {
    return id;
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
