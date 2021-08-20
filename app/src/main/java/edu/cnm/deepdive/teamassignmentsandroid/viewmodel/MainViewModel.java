package edu.cnm.deepdive.teamassignmentsandroid.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.User;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import edu.cnm.deepdive.teamassignmentsandroid.service.GroupRepository;
import edu.cnm.deepdive.teamassignmentsandroid.service.UserRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

/**
 * Prepares and manages data for the activities and fragments and presents it to the UI.
 */
public class MainViewModel extends AndroidViewModel {

  private final UserRepository userRepository;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<User> user;
  private final MutableLiveData<List<Task>> tasks;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<List<Group>> groups;
  private final MutableLiveData<List<Group>> ownedGroups;
  private final CompositeDisposable pending;
  private final GroupRepository groupRepository;

  /**
   * Manages data for acitivities and fragmetns.
   * @param application is only parameter accepted by viewModel constructor.
   */
  public MainViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    account = new MutableLiveData<>();
    user = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    groups = new MutableLiveData<>();
    ownedGroups = new MutableLiveData<>();
    pending = new CompositeDisposable();
    groupRepository = new GroupRepository(application);
    tasks = new MutableLiveData<>();
    userFromServer();
    loadGroups();
//    loadOwnedGroups();
  }

  /**
   * Gets group from model.Group.
   * @return group as live data.
   */
  public LiveData<List<Group>> getGroups() {
    return groups;
  }

  /**
   * Gets Owned groups from management fragment.
   * @return list of owned groups.
   */
  public LiveData<List<Group>> getOwnedGroups() {
    return ownedGroups;
  }

  /**
   * Gets tasks from pojo.
   * @return list of tasks.
   */
  public LiveData<List<Task>> getTasks() {
    return tasks;
  }

  /**
   * Posts Task to thread to set given value.
   * @param groupId The group's id.
   */
  public void loadTasks(long groupId) {
    //TODO invoke repository method to retrieve tasks from server and post into tasks mutable live data.
  }

  private void userFromServer() {
    userRepository.getUserProfile()
        .subscribe(
            user::postValue,
            throwable::postValue
        );
  }

  /**
   * Posts Group to thread to set given value.
   */
  public void loadGroups() {
    throwable.postValue(null);
    pending.add(
        groupRepository.getGroups()
            .subscribe(
                groups::postValue,
                throwable::postValue
            )
    );
  }

  /**
   * Groups will be loaded from the database.
   */
  public void loadOwnedGroups() {
    throwable.postValue(null);
    pending.add(
        groupRepository.getGroups(true)
            .subscribe(
                ownedGroups::postValue,
                throwable::postValue
            )
    );
  }

  /**
   * Groups come from management fragment and gets saved to the database.
   * @param  group will be saved to database.
   */
  public void saveGroup(Group group) {
    throwable.postValue(null);
    pending.add(
        groupRepository.saveGroup(group)
            .subscribe(
                (g) -> loadOwnedGroups(), //TODO explore alternative of adding group to current list of
                //TODO groups we have in live data
                throwable::postValue
            )
    );
  }

  /**
   * This method is called when this ViewModel is no longer used and will be destroyed.
   */
  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
