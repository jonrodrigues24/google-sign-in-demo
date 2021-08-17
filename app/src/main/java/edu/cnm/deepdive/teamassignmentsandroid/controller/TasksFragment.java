package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentTasksBinding;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

public class TasksFragment extends BottomSheetDialogFragment {

  MainViewModel viewModel;
  FragmentTasksBinding binding;


  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //TODO read any arguments passed into fragment.
  }




  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    //TODO return the view inflated while creating the dialog
    FragmentTasksBinding binding = FragmentTasksBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //TODO get an instance of the appropriate view model and observe any relevant live data.
  }
}
