package edu.cnm.deepdive.teamassignmentsandroid.model.pojo;

import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.User;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import java.util.Date;

/**
 * Created fields for Task with getters and setters.
 */
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

  /**
   * Gets the task id
   * @return task id with Long
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets user that created a task.
   * @return returns user as owner of task
   */
  public User getUser() {
    return user;
  }

  /**
   * gets Group
   * @return Group
   */
  public Group getGroup() {
    return group;
  }

  /**
   * Gets Date user posts to database.
   * @return Date of posting.
   */
  public Date getPostDate() {
    return postDate;
  }

  /**
   * Sets Date user posts to database.
   * @param postDate of task
   */
  public void setPostDate(Date postDate) {
    this.postDate = postDate;
  }

  /**
   * Gets due date of tasks.
   * @return Date task is due.
   */
  public Date getDueDate() {
    return dueDate;
  }

  /**
   * Sets date task is due.
   * @param dueDate of task
   */
  public void setDueDate(Date dueDate) {
    this.dueDate = dueDate;
  }

  /**
   * Used to display if task has been bompleted.
   * @return boolean for task completion.
   */
  public boolean isCompleted() {
    return completed;
  }

  /**
   * Sets the task completed true or false.
   * @param completed boolean
   */
  public void setCompleted(boolean completed) {
    this.completed = completed;
  }

  /**
   * User confirms if task was complete.
   * @return boolean of confirmation
   */
  public boolean isConfirmedComplete() {
    return confirmedComplete;
  }

  /**
   * Sets confirmation of task completion
   * @param confirmedComplete boolean
   */
  public void setConfirmedComplete(boolean confirmedComplete) {
    this.confirmedComplete = confirmedComplete;
  }

  /**
   * Gets title of task
   * @return String of title of task
   */
  public String getTitle() {
    return title;
  }

  /**
   * Sets title of task
   * @param title
   */
  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets description of task
   * @return String of task description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of the task
   * @param description of task
   */
  public void setDescription(String description) {
    this.description = description;
  }
}
