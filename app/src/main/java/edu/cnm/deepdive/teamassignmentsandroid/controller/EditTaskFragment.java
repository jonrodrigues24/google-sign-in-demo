package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentEditTaskBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;
import java.util.Calendar;

/**
 * This fragment contains methods to get and make new tasks.  It extends Bottom sheet dialog
 * fragment to populate the bottom pop up.
 */
public class EditTaskFragment extends BottomSheetDialogFragment implements TextWatcher {


  private MainViewModel viewModel;
  private FragmentEditTaskBinding binding;
  private long groupId;
  private long taskId;
  private Task task;

  /**
   * requests group by id for user to add a task
   *
   * @param savedInstanceState extends parcelable, allows instance to be written to
   */
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      EditTaskFragmentArgs editTaskFragmentArgs = EditTaskFragmentArgs.fromBundle(args);
      groupId = editTaskFragmentArgs.getGroupId();
      taskId = editTaskFragmentArgs.getTaskId();
    }
  }

  /**
   * Creates the pop up dialog for task input
   *
   * @param savedInstanceState extends parcelable, allows instance to be written to
   * @return the dialog
   */
  @NonNull
  @Override
  public Dialog onCreateDialog(
      @Nullable Bundle savedInstanceState) {

    Dialog dialog = super.onCreateDialog(savedInstanceState);
    dialog.setOnShowListener((dlg) -> checkSubmitConditions());
    return dialog;
  }

  private void checkSubmitConditions() {
    String name = binding.title.getText().toString().trim();
    binding.submit.setEnabled(!name.isEmpty());
  }

  /**
   * Instantiates an XML layout.
   *
   * @param inflater           to inflate the layout
   * @param container          implements the view group
   * @param savedInstanceState extends the base bundle
   * @return returns layout with on click listeners
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    binding = FragmentEditTaskBinding.inflate(inflater, container, false);
    binding.title.addTextChangedListener(this);
    binding.submit.setOnClickListener((v) -> {
      task.setTitle(binding.title.getText().toString().trim());
      String description = binding.description.getText().toString().trim();
      if (!description.isEmpty()) {
        task.setDescription(description);
      }
      int year = binding.dueDate.getYear();
      int month = binding.dueDate.getMonth();
      int day = binding.dueDate.getDayOfMonth();
      Calendar dueDate = Calendar.getInstance();
      dueDate.set(year, month, day);
      task.setDueDate(dueDate.getTime());
      viewModel.saveTask(groupId, task);
      this.dismiss();
    });
    binding.cancel.setOnClickListener((v) -> this.dismiss());
    return binding.getRoot();
  }

  /**
   * Called immediately after onViewCreate.
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
    if (taskId != 0) {
      viewModel.getTask().observe(getViewLifecycleOwner(), (task) -> {
        Log.d(getClass().getSimpleName(), task.getTitle());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(task.getDueDate());
        this.task = task;
        binding.title.setText(task.getTitle());
        binding.description.setText(task.getDescription());
        binding.dueDate.updateDate(calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
      });
      viewModel.loadTask(groupId, taskId);
    } else {
      task = new Task();
    }

  }

  /**
   * This method is called to notify you that, within s, the count characters beginning at start are
   * about to be replaced by new text with length after.
   *
   * @param s     for char sequence of data
   * @param start the count characters beginning at start
   * @param count the count of characters
   * @param after characters replaced by new text with length after
   */
  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  /**
   * This method is called to notify you that, within s, the count characters beginning at start
   * have just replaced old text that had length before.
   *
   * @param s      for char sequence of data
   * @param start  the count characters beginning at start
   * @param before characters replaced old text that had length before
   * @param count  of characters
   */
  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  /**
   * This method is called to notify you that, somewhere within s, the text has been changed.
   *
   * @param s for char sequence of data
   */
  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }
}
