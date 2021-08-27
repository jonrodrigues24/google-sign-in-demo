package edu.cnm.deepdive.teamassignmentsandroid.model.pojo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.gson.annotations.Expose;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Get and sets user information for application.
 */
public class User {

  @Expose
  private Long id;
  @Expose
  private Date connected;
  @Expose
  private String displayName;
  @Expose
  private Date creationDate;
  private String oauthKey;
  private final List<Group> groups = new LinkedList<>();
  private final List<Task> tasks = new LinkedList<>();

  /**
   * Gets a Long for user id.
   * @return returns long for id.
   */
  public Long getId() {
    return id;
  }

  /**
   * Gets the date/time user connects to service.
   * @return returns the date/time of connection.
   */
  public Date getConnected() {
    return connected;
  }

  /**
   * Gets Date user creates login.
   * @return returns Date of connection.
   */
  public Date getCreationDate() {
    return creationDate;
  }

  /**
   * Gets users display name.
   * @return String of users display name.
   */
  public String getDisplayName() {
    return displayName;
  }

  /**
   * Sets the users display name.
   * @param displayName is String of user's name.
   */
  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  /**
   * Gets the oauth key for the user.
   * @return returns an oauth key for authentication.
   */
  public String getOauthKey() {
    return oauthKey;
  }

  /**
   * Sets the oauth key for user.
   * @param oauthKey return in String format
   */
  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }

  /**
   * Gets list of Groups
   * @return list of groups
   */
  public List<Group> getGroups() {
    return groups;
  }

  /**
   * gets list of tasks
   * @return list of tasks
   */
  public List<Task> getTasks() {
    return tasks;
  }

  @Override
  public int hashCode() {
    return id.hashCode();
  }

  @Override
  public boolean equals(@Nullable Object obj) {
    boolean comparison;
    if (obj == this) {
      comparison = true;
    } else if (obj instanceof User) {
      User other = (User) obj;
      comparison = id != null && id.equals(other.id);
    } else {
      comparison = false;
    }
    return comparison;
  }

  @NonNull
  @Override
  public String toString() {
    return displayName;
  }
}
