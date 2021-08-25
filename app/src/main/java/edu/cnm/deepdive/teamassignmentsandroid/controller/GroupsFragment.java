package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.GroupAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentGroupsBinding;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

/**
 * This class creates the binding and inflates the Fragment layout and pasess groups and group ids
 * to the view model.
 */
public class GroupsFragment extends Fragment {

  private MainViewModel viewModel;
  private FragmentGroupsBinding binding;

  /**
   * Called to have the fragment instantiate its user interface view
   *
   * @param inflater           Instantiates a layout XML file into its corresponding View objects.
   * @param container          The view group is the base class for layouts and views containers
   * @param savedInstanceState A mapping from String keys to various Parcelable values.
   * @return returns a rectangular area on the screen and is responsible for drawing and event
   * handling
   */
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentGroupsBinding.inflate(inflater, container, false);
    binding.createGroup.setOnClickListener((v) ->
        Navigation.findNavController(binding.getRoot())
            .navigate(GroupsFragmentDirections.editGroup()));
    return binding.getRoot();
  }

  /**
   * Called after onCreateView has returned, used to initialize subclasses before saved state is
   * restored to view
   *
   * @param view               View is the base class for widgets, which are used to create
   *                           interactive UI components
   * @param savedInstanceState A mapping from String keys to various Parcelable values
   */
  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(getActivity()).get(MainViewModel.class);
    viewModel.getGroups().observe(getViewLifecycleOwner(), (groups) ->
        binding.groups.setAdapter(new GroupAdapter(groups, getContext(),
            (v, groupId) -> onGroupTasksClick(groupId),
            (v, groupId) -> onGroupEditClick(groupId),
            (v, groupId) -> onGroupDeleteClick(groupId))));
  }

  private void onGroupEditClick(long groupId) {
    Navigation.findNavController(binding.getRoot())
        .navigate(GroupsFragmentDirections.editGroup().setGroupId(groupId));
  }


  /**
   * Helper method that passes group id to the viewholder.
   */
  public void onGroupTasksClick(long groupId) {
    GroupsFragmentDirections.OpenTasks toTaskFragment
        = GroupsFragmentDirections.openTasks(groupId);
    Navigation.findNavController(binding.getRoot()).navigate(toTaskFragment);
  }

  private void onGroupDeleteClick(long groupId) {
    viewModel.deleteGroup(groupId);
  }

}
