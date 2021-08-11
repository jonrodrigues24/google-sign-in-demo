package edu.cnm.deepdive.teamassignmentsandroid;

import android.app.Application;
import edu.cnm.deepdive.teamassignmentsandroid.service.GoogleSignInService;

public class SignInApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    GoogleSignInService.setContext(this);
  }
}
