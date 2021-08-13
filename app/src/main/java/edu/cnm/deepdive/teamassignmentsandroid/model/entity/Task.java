package edu.cnm.deepdive.teamassignmentsandroid.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;

@Entity(
  foreignKeys = {
    @ForeignKey(
        entity = Group.class,
        childColumns = {"group_id"},
        parentColumns = {"group_id"},
        onDelete = ForeignKey.CASCADE
    )
  }
)
public class Task {


  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "task_id")
  private long id;

  @Expose
  @ColumnInfo(name = "post_date")
  private Date postDate = new Date();

  @Expose
  @ColumnInfo(name = "due_date", index = true)
  @NonNull
  private Date dueDate;

  @Expose
  @NonNull
  @ColumnInfo(name = "group_id")
  private long groupId;

  @Expose
  private boolean completed;

  @Expose
  @ColumnInfo(name = "confirmed_complete")
  private boolean confirmedComplete;




}
