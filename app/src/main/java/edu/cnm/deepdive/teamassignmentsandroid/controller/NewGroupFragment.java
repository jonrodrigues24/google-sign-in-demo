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

public class NewGroupFragment extends BottomSheetDialogFragment implements TextWatcher {

  private MainViewModel viewModel;
  private FragmentNewGroupBinding binding;


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
