package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentNewGroupBinding;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentNewTaskBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;
import java.util.Calendar;
import java.util.Date;

public class NewTaskFragment extends BottomSheetDialogFragment implements TextWatcher {

  public static final String GROUP_ID_KEY = "group_id";

  private MainViewModel viewModel;
  private FragmentNewTaskBinding binding;
  private long groupId;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      groupId = args.getLong(GROUP_ID_KEY);
    }
  }

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

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    //TODO return the view inflated while creating the dialog
    binding = FragmentNewTaskBinding.inflate(inflater, container, false);
    binding.title.addTextChangedListener(this);
    binding.submit.setOnClickListener((v) -> {
      Task task = new Task();
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

  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);

  }

  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }
}
