package edu.cnm.deepdive.teamassignmentsandroid.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.Task;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    Single<Long> insert(Task task);

    @Insert
    Single<List<Long>> insert(Task... tasks);

    @Insert
    Single<List<Long>> insert(Collection<? extends Task> tasks);

    @Update
    Single<Integer> update(Task task);

    @Update
    Single<Integer> update(Task... tasks);

    @Update
    Single<Integer> update(Collection<? extends Task> tasks);

    @Delete
    Single<Integer> delete(Task task);

    @Delete
    Single<Integer> delete(Task... tasks);

    @Delete
    Single<Integer> delete(Collection<? extends Task> tasks);

    @Transaction
    @Query("SELECT * FROM task WHERE task_id = :taskId")
    LiveData<Task> select(long taskId);

}
