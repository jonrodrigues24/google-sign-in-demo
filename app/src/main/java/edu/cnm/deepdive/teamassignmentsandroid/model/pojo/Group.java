package edu.cnm.deepdive.teamassignmentsandroid.model.pojo;

import androidx.annotation.NonNull;
import com.google.gson.annotations.Expose;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * Created fields for Group with getters and setters.
 */
public class Group {
@Expose
  private String name;
@Expose
  private long id;
  @Expose
  private User owner;
  @Expose
  private Date creationDate;
  private final List<Task> tasks = new LinkedList<>();
  private final Set<User> users = new LinkedHashSet<>();
  private boolean currentUserOwner;

  /**
   * Gets the name of the group
   * @return name of group
   */
  public String getName() {
    return name;
  }

  /**
   * gets the id of group using a Long
   * @return id of group
   */
  public long getId() {
    return id;
  }

  /**
   * Sets name of the group
   * @param name of group
   */
  public void setName(String name) {
    this.name = name;
  }

  /**
   * gets the user/ owner of the group
   * @return the owner of the group
   */
  public User getOwner() {
    return owner;
  }

  /**
   * sets the owner of the group
   * @param owner is authenticated as user
   */
  public void setOwner(User owner) {
    this.owner = owner;
  }

  /**
   * gets the Date the user created a group
   * @return Date group created
   */
  public Date getCreationDate() {
    return creationDate;
  }

  /**
   * Sets the Date group was created
   * @param creationDate of group
   */
  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  /**
   * Gets a list of task for a group
   * @return list of tasks
   */
  public List<Task> getTasks() {
    return tasks;
  }



  /**
   * Sets the user/owner of a group
   * @return user of group
   */
  public Set<User> getUsers() {
    return users;
  }



  public boolean isCurrentUserOwner() {
    return currentUserOwner;
  }

  public void setCurrentUserOwner(boolean currentUserOwner) {
    this.currentUserOwner = currentUserOwner;
  }

  /**
   * Names the group
   * @return string to name group
   */
  @NonNull
  @Override
  public String toString() {
    return name;
  }
}
