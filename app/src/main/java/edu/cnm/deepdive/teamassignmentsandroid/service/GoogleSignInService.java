package edu.cnm.deepdive.teamassignmentsandroid.service;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import edu.cnm.deepdive.teamassignmentsandroid.BuildConfig;
import io.reactivex.Single;

/**
 * Uses Google Sign-In to authenticate a user with Google credentials.
 */
public class GoogleSignInService {

  private static Application context;

  private final GoogleSignInClient client;

  private GoogleSignInAccount account;

  private static final String BEARER_TOKEN_FORMAT = "Bearer %s";

  private GoogleSignInService() {
    GoogleSignInOptions options = new GoogleSignInOptions.Builder()
        .requestEmail()
        .requestId()
        .requestProfile()
        .requestIdToken(BuildConfig.CLIENT_ID)
        .build();
    client = GoogleSignIn.getClient(context, options);
  }

  /**
   * Sets context for Application.
   * @param context
   */
  public static void setContext(Application context) {
    GoogleSignInService.context = context;
  }

  /**
   * Gets Instance of google sign in service.
   * @return returns instance of google sign in serive.
   */
  public static GoogleSignInService getInstance() {
    return InstanceHolder.INSTANCE;
  }

  /**
   * Will retrieve users account once sign in is successful.
   * @return users account once authenticated.
   */
  public GoogleSignInAccount getAccount() {
    return account;
  }

  /**
   * Will refresh users account for secure connection without need to log in again.
   * @return return single account
   */
  public Single<GoogleSignInAccount> refresh() {
    return Single.create((emitter) ->
        client.silentSignIn()
        .addOnSuccessListener(this::setAccount)
        .addOnSuccessListener(emitter::onSuccess)
        .addOnFailureListener(emitter::onError)
    );
  }

  /**
   * User credentials will be sent for authentication.
   * @return bearer token on refresh.
   */
  public Single<String> refreshBearerToken() {
    return refresh()
        .map((account) -> String.format(BEARER_TOKEN_FORMAT, account.getIdToken()));
  }

  /**
   * Starts sign in process for login activity.
   * @param activity making the call
   * @param requestCode to verify account
   */
  public void startSignIn(Activity activity, int requestCode) {
    account = null;
    Intent intent = client.getSignInIntent();
    activity.startActivityForResult(intent, requestCode);
  }

  /**
   * Task to complete the sign in process.
   * @param data for Intent of user
   * @return Task to complete sign in.
   */
  public Task<GoogleSignInAccount> completeSignIn(Intent data) {
    Task<GoogleSignInAccount> task = null;
    try {
      task = GoogleSignIn.getSignedInAccountFromIntent(data);
      setAccount(task.getResult(ApiException.class));
    } catch (ApiException e) {
      // Exception will be passed automatically to onFailureListener.
    }
    return task;
  }

  /**
   * Task to sign user out of account.
   * @return Task to sign out user.
   */
  public Task<Void> signOut() {
    return client.signOut()
        .addOnCompleteListener((ignored) -> setAccount(null));
  }

  private void setAccount(GoogleSignInAccount account) {
    this.account = account;
    if(account != null) {
      Log.d(getClass().getSimpleName(), account.getIdToken());
    }
  }

  private static class InstanceHolder {

    private static final GoogleSignInService INSTANCE = new GoogleSignInService();

  }


}
