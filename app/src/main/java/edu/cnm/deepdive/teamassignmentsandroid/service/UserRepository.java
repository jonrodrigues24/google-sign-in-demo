package edu.cnm.deepdive.teamassignmentsandroid.service;

import android.content.Context;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.User;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

/**
 * Fetches User data from WebServiceProxy.
 */
public class UserRepository {

  private final Context context;
  private final WebServiceProxy webService;
  private final GoogleSignInService signInService;

  /**
   * Passes Context for Google Sign in Service.
   * @param context is this instance of the repository.
   */
  public UserRepository(Context context) {
    this.context = context;
    webService = WebServiceProxy.getInstance();
    signInService = GoogleSignInService.getInstance();
  }

  /**
   * Sets user, gets bearer token for authentication.
   * @return scheduler for IO work after token is received.
   */
  public Single<User> getUserProfile() {
    return signInService.refresh()
        .observeOn(Schedulers.io())
        .flatMap((account) -> webService.getProfile(getBearerToken(account.getIdToken())))
        .subscribeOn(Schedulers.io());
  }

  private String getBearerToken(String idToken) {
    return String.format("Bearer %s", idToken);
  }
}
