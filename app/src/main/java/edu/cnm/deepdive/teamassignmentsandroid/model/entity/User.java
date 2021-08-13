package edu.cnm.deepdive.teamassignmentsandroid.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;


@Entity(
    tableName = "user",
    indices = {
            @Index(value = {"oauth_key"}, unique = true),
    }
)
public class User {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "user_id")
  private Long id;

  @NonNull
  private Date connected;

  @NonNull
  @ColumnInfo(name = "display_name")
  private String displayName;

  @NonNull
  private Date creationDate = new Date();

  @NonNull
  @ColumnInfo(name = "oauth_key")
  private String oauthKey;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Date getConnected() {
    return connected;
  }

  public void setConnected(Date connected) {
    this.connected = connected;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public Date getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(Date creationDate) {
    this.creationDate = creationDate;
  }

  public String getOauthKey() {
    return oauthKey;
  }

  public void setOauthKey(String oauthKey) {
    this.oauthKey = oauthKey;
  }
}
