package edu.cnm.deepdive.teamassignmentsandroid;

import android.app.Application;
import edu.cnm.deepdive.teamassignmentsandroid.service.GoogleSignInService;

/**
 *  Uses Google Sign-In to authenticate a user with Google credentials.
 */
public class SignInApplication extends Application {

  /**
   * Called when the activity is first created.
   */
  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
  }
}

