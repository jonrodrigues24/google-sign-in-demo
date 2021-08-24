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
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentEditGroupBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

/**
 * This fragment contains methods to get and make new groups.  It extends Bottom sheet dialog
 * fragment to populate the bottom pop up.
 */
public class EditGroupFragment extends BottomSheetDialogFragment implements TextWatcher {

  private MainViewModel viewModel;
  private FragmentEditGroupBinding binding;
  private long groupId;
  private Group group;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      groupId = EditGroupFragmentArgs.fromBundle(args).getGroupId();
    }
  }

  /**
   * Creates the pop up dialog for inputing group data.
   *
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
    //TODO return the view inflated while creating the dialog
    binding = FragmentEditGroupBinding.inflate(inflater, container, false);
    binding.name.addTextChangedListener(this);
    binding.submit.setOnClickListener((v) -> {
      group.setName(binding.name.getText().toString().trim());
      viewModel.saveGroup(group);
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
    Log.d(getClass().getSimpleName(), String.valueOf(groupId));
    if (groupId != 0) {
      viewModel.getGroup().observe(getViewLifecycleOwner(), (group) -> {
        Log.d(getClass().getSimpleName(), group.getName());
        this.group = group;
        binding.name.setText(group.getName());
      });
      viewModel.loadGroup(groupId);
    } else {
      this.group = new Group();
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
