package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.TaskAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentTasksBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

/**
 * This fragment contains methods to get and set tasks to a group.  It extends Bottom sheet dialog
 * fragment to populate the bottom pop up.
 */
public class TasksFragment extends Fragment {

  /**
   * Gets the id of a group from the database.
   */
  private MainViewModel viewModel;
  private FragmentTasksBinding binding;
  private long groupId;
  private LiveData<Task> taskLiveData;

  /**
   * Task fragment is initialized.
   *
   * @param savedInstanceState of Bundle
   */
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (getArguments() != null) {
      groupId = TasksFragmentArgs.fromBundle(args).getGroupId();
      Log.d(getClass().getSimpleName(), String.valueOf(groupId));
    }
  }


  /**
   * Instantiates an XML layout.
   *
   * @param inflater           to inflate the layout
   * @param container          implements the view group
   * @param savedInstanceState extends the base bundle
   * @return a rectangular area on the screen and is responsible for drawing and event handling
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentTasksBinding.inflate(inflater, container, false);
    binding.createTask.setOnClickListener((v) -> Navigation.findNavController(binding.getRoot())
        .navigate(TasksFragmentDirections.editTask(groupId)));
    return binding.getRoot();
  }

  /**
   * CAlled immediately after onViewCreate.
   *
   * @param view               expands the layout and widgets
   * @param savedInstanceState extends teh base bundle
   */
  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    //noinspection ConstantConditions
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getTasks().observe(getViewLifecycleOwner(), (tasks) -> {
      TaskAdapter adapter = new TaskAdapter(tasks, getContext(), (v, taskId) -> {
        Navigation.findNavController(binding.getRoot()).navigate(
            TasksFragmentDirections.editTask(groupId).setTaskId(taskId)
        );
      }, (v, taskId) -> onTaskDeleteClick(groupId,taskId));
      binding.tasks.setAdapter(adapter);
    });
    viewModel.getGroup().observe(getViewLifecycleOwner(), (group) -> {
      AppCompatActivity activity = (AppCompatActivity) getActivity();
      ActionBar actionBar = activity.getSupportActionBar();
      actionBar.setTitle(getString(R.string.tasks_title_format,actionBar.getTitle(), group.getName()));
    });
    viewModel.loadTasks(groupId);
    viewModel.loadGroup(groupId);

  }

  private void onTaskDeleteClick(long groupId, long taskId) {
    viewModel.deleteTask(groupId, taskId);
  }


}
