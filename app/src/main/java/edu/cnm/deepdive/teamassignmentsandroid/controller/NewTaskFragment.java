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

/**
 * This fragment contains methods to get and make new tasks.  It extends Bottom sheet dialog fragment
 * to populate the bottom pop up.
 */
public class NewTaskFragment extends BottomSheetDialogFragment implements TextWatcher {

  /**
   * group id is required to assign a task to the group
   */
  public static final String GROUP_ID_KEY = "group_id";

  private MainViewModel viewModel;
  private FragmentNewTaskBinding binding;
  private long groupId;

  /**
   * requests group by id for user to add a task
   * @param savedInstanceState extends parcelable, allows instance to be written to
   */
  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      groupId = args.getLong(GROUP_ID_KEY);
    }
  }

  /**
   * Creates the pop up dialog for task input
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
   * @param inflater to inflate the layout
   * @param container implements the view group
   * @param savedInstanceState extends the base bundle
   * @return returns layout with on click listeners
   */
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

  /**
   * Called immediately after onViewCreate.
   * @param view expands the layout and widgets
   * @param savedInstanceState extends teh base bundle
   */
  @Override
  public void onViewCreated(@NonNull View view,
      @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);

  }

  /**
   *This method is called to notify you that, within s, the count characters beginning at start are about to be replaced by new text with length after.
   * @param s for char sequence of data
   * @param start the count characters beginning at start
   * @param count the count of characters
   * @param after characters replaced by new text with length after
   */
  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  /**
   *This method is called to notify you that, within s, the count characters beginning at start have just replaced old text that had length before.
   * @param s for char sequence of data
   * @param start the count characters beginning at start
   * @param before characters replaced old text that had length before
   * @param count of characters
   */
  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {

  }

  /**
   *This method is called to notify you that, somewhere within s, the text has been changed.
   * @param s for char sequence of data
   */
  @Override
  public void afterTextChanged(Editable s) {
    checkSubmitConditions();
  }
}
