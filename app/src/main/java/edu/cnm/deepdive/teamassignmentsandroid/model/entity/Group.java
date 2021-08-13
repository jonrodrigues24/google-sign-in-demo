package edu.cnm.deepdive.teamassignmentsandroid.model.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.Expose;
import java.util.Date;

@Entity(

    foreignKeys = {
        @ForeignKey(
            entity = User.class,
            childColumns = {"owner_id"},
            parentColumns = {"user_id"},
            onDelete = ForeignKey.CASCADE
        )
    }

)
public class Group {

  @PrimaryKey(autoGenerate = true)
  @ColumnInfo(name = "group_id")
  private long id;

  @Expose
  @NonNull
  private String name;

  @Expose
  @ColumnInfo(name = "creation_date", index = true)
  @NonNull
  private Date CreationDate = new Date();

  @Expose
  @NonNull
  @ColumnInfo(name = "owner_id")
  private long ownerId;

}
