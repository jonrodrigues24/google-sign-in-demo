package edu.cnm.deepdive.teamassignmentsandroid.model.pojo;

import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.User;
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

  private String title;

  private String description;

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

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
