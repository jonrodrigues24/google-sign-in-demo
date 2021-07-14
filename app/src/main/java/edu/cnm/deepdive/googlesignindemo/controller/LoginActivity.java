package edu.cnm.deepdive.googlesignindemo.controller;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import edu.cnm.deepdive.googlesignindemo.R;
import edu.cnm.deepdive.googlesignindemo.databinding.ActivityLoginBinding;
import edu.cnm.deepdive.googlesignindemo.service.GoogleSignInService;

public class LoginActivity extends AppCompatActivity {

  private static final int LOGIN_REQUEST_CODE = 1000;

  private GoogleSignInService service;
  private ActivityLoginBinding binding;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    service = GoogleSignInService.getInstance();
    service.refresh()
        .addOnSuccessListener((account) -> {/* Switch to the MainActivity*/})
        .addOnFailureListener((throwable) -> {
          binding = ActivityLoginBinding.inflate(getLayoutInflater());
          binding.signIn.setOnClickListener((v) ->
            service.startSignIn(this, LOGIN_REQUEST_CODE));
          setContentView(binding.getRoot());
        });
  }
}