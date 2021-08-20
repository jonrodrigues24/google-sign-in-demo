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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentNewGroupBinding;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentTasksBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;
import org.jetbrains.annotations.NotNull;

/**
 * This fragment contains methods to get and made new groups.  It extends Bottom sheet dialog fragment
 * to populate the bottom pop up.
 */
public class NewGroupFragment extends BottomSheetDialogFragment implements TextWatcher {

  private MainViewModel viewModel;
  private FragmentNewGroupBinding binding;

  /**
   * Creates the pop up dialog for inputing group data.
   * @param savedInstanceState extends bundle to enable data to be written.
   * @return the dialog for input
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
    String name = binding.name.getText().toString().trim();
    binding.submit.setEnabled(!name.isEmpty());
  }

  /**
   * Instantiates an XML layout.
   * @param inflater to inflate the layout
   * @param container implements the view group
   * @param savedInstanceState extends the base bundle
   * @return the binding layout
   */
  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    //TODO return the view inflated while creating the dialog
    binding = FragmentNewGroupBinding.inflate(inflater, container, false);
    binding.name.addTextChangedListener(this);
    binding.submit.setOnClickListener((v) -> {
      Group group = new Group();
      group.setName(binding.name.getText().toString().trim());
      viewModel.saveGroup(group);
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
