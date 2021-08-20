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

  /**
   * Gets List of groups.
   * @param ownedOnly are groups authenticated by user.
   * @return list of groups owned by user.
   */
  public Single<List<Group>> getGroups(boolean ownedOnly) {

    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> webService.getGroups(ownedOnly, bearerToken));

  }

  /**
   * Saves sing group to data base.
   * @param group
   * @return group and bearer token
   */
  public Single<Group> saveGroup(Group group) {

    return signInService.refreshBearerToken()
        .observeOn(Schedulers.io())
        .flatMap((bearerToken) -> webService.postGroup(group, bearerToken));

  }
}
