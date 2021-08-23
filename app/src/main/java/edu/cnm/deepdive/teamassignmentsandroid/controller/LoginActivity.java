package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.content.Intent;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.teamassignmentsandroid.service.GoogleSignInService;

/**
 * This class offers the user a sign in button, then authenticates account with google sign in service.
 */
public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;

  private GoogleSignInService service;
  private ActivityLoginBinding binding;

  /**
   * Called when the activity is starting followed by initilization including tab layout.
   * @param savedInstanceState A mapping from String keys to various Parcelable values.
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    service = GoogleSignInService.getInstance();
    service.refresh()
        .subscribe(
            this::switchToMain,
            (throwable) -> {
              binding = ActivityLoginBinding.inflate(getLayoutInflater());
              binding.signIn.setOnClickListener((v) ->
                  service.startSignIn(this, LOGIN_REQUEST_CODE));
              setContentView(binding.getRoot());
            });
  }

  /**
   * On start up activity will fetch account information and  return a toast if credentials are required to log in.
   * @param requestCode is set at 1000 for "pass phrase"
   * @param resultCode result code required to pass activity
   * @param data can be null, user will need to create account
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
    if (requestCode == LOGIN_REQUEST_CODE) {
      service.completeSignIn(data)
          .addOnSuccessListener(this::switchToMain)
          .addOnFailureListener((throwable) ->
              Toast.makeText(this, "Unable to sign in with the provided credentials.",
                  Toast.LENGTH_LONG).show());
    } else {
      super.onActivityResult(requestCode, resultCode, data);
    }

  }

  //TODO Javadocs here.
  private void switchToMain(GoogleSignInAccount account) {
    Intent intent = new Intent(this, MainActivity.class)
        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(intent);
  }
}