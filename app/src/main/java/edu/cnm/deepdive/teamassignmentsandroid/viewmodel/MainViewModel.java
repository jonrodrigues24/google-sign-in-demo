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
    loadOwnedGroups();
  }

  public LiveData<List<Group>> getGroups() {
    return groups;
  }

  public LiveData<List<Group>> getOwnedGroups() {
    return ownedGroups;
  }

  public LiveData<List<Task>> getTasks() {
    return tasks;
  }

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

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
