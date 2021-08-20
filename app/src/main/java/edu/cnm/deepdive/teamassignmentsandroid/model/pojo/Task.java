package edu.cnm.deepdive.teamassignmentsandroid.model.pojo;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.User;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import java.util.Date;

public class Task {

  @Expose
  private Long id;
  @Expose
  private User user;
  @Expose
  private Group group;
  @Expose
  private Date postDate;
  @Expose
  private Date dueDate;
  @Expose
  private boolean completed;
  @Expose
  private boolean confirmedComplete;
  @Expose
  private String title;
  @Expose
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
