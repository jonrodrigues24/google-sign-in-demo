package edu.cnm.deepdive.teamassignmentsandroid.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;
import androidx.room.Update;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import io.reactivex.Single;
import java.util.Collection;
import java.util.List;

@Dao
public interface UserDao {

    @Insert
    Single<Long> insert(User user);

    @Insert
    Single<List<Long>> insert(User... users);

    @Insert
    Single<List<Long>> insert(Collection<? extends User> users);

    @Update
    Single<Integer> update(User user);

    @Update
    Single<Integer> update(User... users);

    @Update
    Single<Integer> update(Collection<? extends User> users);

    @Delete
    Single<Integer> delete(User user);

    @Delete
    Single<Integer> delete(User... users);

    @Delete
    Single<Integer> delete(Collection<? extends User> users);

    @Transaction
    @Query("SELECT * FROM user WHERE user_id = :userId")
    LiveData<User> select(long userId);

}
