package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import org.jetbrains.annotations.NotNull;

public class ManagementFragment extends Fragment {

  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    return inflater.inflate(R.layout.fragment_mangement, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }
}