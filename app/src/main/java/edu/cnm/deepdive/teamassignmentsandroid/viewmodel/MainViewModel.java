package edu.cnm.deepdive.teamassignmentsandroid.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Lifecycle.Event;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.OnLifecycleEvent;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.service.GroupRepository;
import edu.cnm.deepdive.teamassignmentsandroid.service.UserRepository;
import io.reactivex.disposables.CompositeDisposable;
import java.util.List;

public class MainViewModel extends AndroidViewModel {

  private final UserRepository userRepository;
  private final MutableLiveData<GoogleSignInAccount> account;
  private final MutableLiveData<User> user;
  private final MutableLiveData<Throwable> throwable;
  private final MutableLiveData<List<Group>> groups;
  private final CompositeDisposable pending;
  private final GroupRepository groupRepository;
  
  public MainViewModel(@NonNull Application application) {
    super(application);
    userRepository = new UserRepository(application);
    account = new MutableLiveData<>();
    user = new MutableLiveData<>();
    throwable = new MutableLiveData<>();
    groups = new MutableLiveData<>();
    pending = new CompositeDisposable();
    groupRepository = new GroupRepository(application);
    userFromServer();
    loadGroups();
  }

  public LiveData<List<Group>> getGroups() {
    return groups;
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

  @OnLifecycleEvent(Event.ON_STOP)
  private void clearPending() {
    pending.clear();
  }
}
