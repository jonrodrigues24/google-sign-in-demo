package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentMangementBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

public class ManagementFragment extends Fragment implements TextWatcher {

  private FragmentMangementBinding binding;
  private MainViewModel viewModel;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentMangementBinding.inflate(inflater, container, false);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    binding.createGroup.addTextChangedListener(this);
    return binding.getRoot();
  }

  private void saveGroup() {
    Group group = new Group();
    @SuppressWarnings("ConstantConditions")
    String name = binding.createGroup.getText().toString().trim();
    group.setName(name);
    viewModel.saveGroup(group);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
  }


  @Override
  public void beforeTextChanged(CharSequence s, int start, int count, int after) {

  }

  @Override
  public void onTextChanged(CharSequence s, int start, int before, int count) {
    saveGroup();
  }

  @Override
  public void afterTextChanged(Editable s) {

  }
}