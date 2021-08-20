package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import edu.cnm.deepdive.teamassignmentsandroid.NavGraphDirections;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.GroupAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.GroupAdapter.OnGroupClickListener;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

public class HomeFragment extends Fragment implements OnGroupClickListener {

  private MainViewModel viewModel;
  private FragmentHomeBinding binding;

  /**
   * Called to have the fragment instantiate its user interface view
   * @param inflater Instantiates a layout XML file into its corresponding View objects.
   * @param container The view group is the base class for layouts and views containers
   * @param savedInstanceState A mapping from String keys to various Parcelable values.
   * @return returns a rectangular area on the screen and is responsible for drawing and event handling
   */
  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
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
    viewModel.getGroups().observe(getViewLifecycleOwner(), (groups) ->
        binding.recyclerView.setAdapter(new GroupAdapter(groups, getContext(),
            (v, groupId) ->
            {
              TasksFragment fragment = new TasksFragment();
              Bundle args = new Bundle();
              args.putLong(TasksFragment.GROUP_ID_KEY, groupId);
              fragment.setArguments(args);
              fragment.show(getChildFragmentManager(), fragment.getClass().getName());
            }
        )));
  }


  /**
   * Helper method that passes group id to the viewholder.
   */
  @Override
  public void onGroupClick(View view, long groupId) {
    NavGraphDirections.OpenTasks toTaskFragment
        = NavGraphDirections.openTasks(groupId);
//    toTaskFragment.setGroupTasks(groupId)
    //TODO set task array using group
    Navigation.findNavController(binding.getRoot()).navigate(toTaskFragment);
  }
}
