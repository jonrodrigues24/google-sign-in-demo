package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentTasksBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

public class TasksFragment extends BottomSheetDialogFragment {

  public static final String GROUP_ID_KEY = "group_id";
  private MainViewModel viewModel;
  private FragmentTasksBinding binding;
  private long groupId;
  private LiveData<Task> taskLiveData;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //TODO read any arguments passed into fragment.
    if (getArguments() != null) {
      groupId = getArguments().getLong(GROUP_ID_KEY);
      Log.d(getClass().getSimpleName(), String.valueOf(groupId));
    }
  }

//bottom fragment doesn't use all the stuff a popup dialog fragment needs, do not need onCreateDialog.


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
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getTasks().observe(getViewLifecycleOwner(), (tasks) -> {
      //TODO create adapter and pass tasks to constructor. then attack adapter to recycler view.
    });
    viewModel.loadTasks(groupId);
  }
}
