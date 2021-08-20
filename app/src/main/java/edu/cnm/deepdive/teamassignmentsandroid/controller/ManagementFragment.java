package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentMangementBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;
import org.jetbrains.annotations.NotNull;

public class ManagementFragment extends Fragment {

  private FragmentMangementBinding binding;
  private MainViewModel viewModel;

  /**
   * Called to have the fragment instantiate its user interface view
   * @param inflater Instantiates a layout XML file into its corresponding View objects.
   * @param container The view group is the base class for layouts and views containers
   * @param savedInstanceState A mapping from String keys to various Parcelable values.
   * @return a rectangular area on the screen and is responsible for drawing and event handling
   */
  @Nullable
  @Override
  public View onCreateView(
      LayoutInflater inflater,
      ViewGroup container,
      Bundle savedInstanceState
  ) {
    binding = FragmentMangementBinding.inflate(inflater, container, false);
    binding.createGroup.setOnClickListener((v) -> {
      NewGroupFragment fragment = new NewGroupFragment();
      //this is where you attach arguments if you want to pass data to the fragment.
      fragment.show(getParentFragmentManager(), fragment.getClass().getName());
    });
    return binding.getRoot();
  }

  /**
   * Called after onCreateView has returned, used to initialize subclasses before saved state is restored to view
   * @param view View is the base class for widgets, which are used to create interactive UI components
   * @param savedInstanceState A mapping from String keys to various Parcelable values
   */
  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getOwnedGroups().observe(getViewLifecycleOwner(), (groups) -> {
      ArrayAdapter<Group> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item, groups);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      binding.groupManagement.setAdapter(adapter);
    });
  }

  /**
   * Called when the fragment is visible to the user and actively running.
   */
  @Override
  public void onResume() {
    super.onResume();
    viewModel.loadOwnedGroups();
  }
}