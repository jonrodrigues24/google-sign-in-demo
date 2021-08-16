package edu.cnm.deepdive.teamassignmentsandroid.model;

import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import java.util.Date;

public class Task {


  private Long id;
  private User user;
  private Group group;
  private Date postDate;
  private Date dueDate;
  private boolean completed;
  private boolean confirmedComplete;

  public Long getId() {
    return id;
  }

  public User getUser() {
    return user;
  }

  public Group getGroup() {
    return group;
  }

  public Date getPostDate() {
    return postDate;
  }

  public void setPostDate(Date postDate) {
    this.postDate = postDate;
  }

  public Date getDueDate() {
    return dueDate;
  }

  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  public boolean isCompleted() {
    return completed;
  }

  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  public boolean isConfirmedComplete() {
    return confirmedComplete;
  }

  public void setConfirmedComplete(boolean confirmedComplete) {
    this.confirmedComplete = confirmedComplete;
  }
}
