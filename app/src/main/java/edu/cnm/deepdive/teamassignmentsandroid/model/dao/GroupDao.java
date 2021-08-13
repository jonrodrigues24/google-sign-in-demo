package edu.cnm.deepdive.teamassignmentsandroid.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.Task;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

public interface GroupDao {

  @Insert
  Single<Long> insert(Group group);

  @Insert
  Single<List<Long>> insert(Group... groups);

  @Insert
  Single<List<Long>> insert(Collection<? extends Group> groups);

  @Update
  Single<Integer> update(Group group);

  @Update
  Single<Integer> update(Group... groups);

  @Update
  Single<Integer> update(Collection<? extends Group> groups);

  @Delete
  Single<Integer> delete(Group group);

  @Delete
  Single<Integer> delete(Group... groups);

  @Delete
  Single<Integer> delete(Collection<? extends Group> groups);

  @Transaction
  @Query("SELECT * FROM `group` WHERE group_id = :groupId")
  LiveData<Group> select(long groupId);

}
