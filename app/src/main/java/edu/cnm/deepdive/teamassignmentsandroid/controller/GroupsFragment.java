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
import edu.cnm.deepdive.teamassignmentsandroid.controller.ConfirmationFragment.Action;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentGroupsBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;
import java.io.Serializable;

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
            (v, group) -> onGroupTasksClick(group),
            (v, group) -> onGroupEditClick(group),
            (v, group) -> onGroupDeleteClick(group))));
  }

  private void onGroupEditClick(Group group) {
    Navigation.findNavController(binding.getRoot())
        .navigate(GroupsFragmentDirections.editGroup().setGroupId(group.getId()));
  }


  /**
   * Helper method that passes group id to the viewholder.
   */
  public void onGroupTasksClick(Group group) {
    GroupsFragmentDirections.OpenTasks toTaskFragment
        = GroupsFragmentDirections.openTasks(group.getId());
    Navigation.findNavController(binding.getRoot()).navigate(toTaskFragment);
  }

  private void onGroupDeleteClick(Group group) {
    Navigation.findNavController(binding.getRoot())
        .navigate(GroupsFragmentDirections.confirmGroupDelete(group.getId(), group.getName(),
            (Serializable & Action) () ->
                viewModel.deleteGroup(group.getId())));
//    viewModel.deleteGroup(groupId);
  }

}
