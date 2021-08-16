package edu.cnm.deepdive.teamassignmentsandroid.service;

import android.content.Context;
import androidx.lifecycle.LiveData;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.entity.User;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

public class GroupRepository {

  private final Context context;
  private final GoogleSignInService signInService;
  private final WebServiceProxy webService;

  public GroupRepository(Context context) {
    this.context = context;
    webService = WebServiceProxy.getInstance();
    signInService = GoogleSignInService.getInstance();
  }

  public Single<List<Group>> getGroups() {

    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getGroups);

  }

}
