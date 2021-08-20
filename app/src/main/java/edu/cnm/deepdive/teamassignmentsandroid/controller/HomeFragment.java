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

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getGroups().observe(getViewLifecycleOwner(), (groups) ->
        binding.groups.setAdapter(new GroupAdapter(groups, getContext(),
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


  @Override
  public void onGroupClick(View view, long groupId) {
    NavGraphDirections.OpenTasks toTaskFragment
        = NavGraphDirections.openTasks(groupId);
//    toTaskFragment.setGroupTasks(groupId)
    //TODO set task array using group
    Navigation.findNavController(binding.getRoot()).navigate(toTaskFragment);
  }
}
