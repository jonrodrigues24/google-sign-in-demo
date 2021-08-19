package edu.cnm.deepdive.teamassignmentsandroid.service;

import android.content.Context;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
import java.util.List;

/**
 * Fetches "Group" data from web service proxy.
 */
public class GroupRepository {

  private final Context context;
  private final GoogleSignInService signInService;
  private final WebServiceProxy webService;

  /**
   * Passes context for google sign in.
   * @param context is received for current process.
   */
  public GroupRepository(Context context) {
    this.context = context;
    webService = WebServiceProxy.getInstance();
    signInService = GoogleSignInService.getInstance();
  }

  /**
   * Sets List of group, gets bearer token for authentication.
   * @return groups if bear token is authenticated.
   */
  public Single<List<Group>> getGroups() {

    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap(webService::getGroups);

  }

}
