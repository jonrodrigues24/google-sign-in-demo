package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.GroupAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;
import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

  private MainViewModel viewModel;
  private FragmentHomeBinding binding;

  @Nullable
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    binding = FragmentHomeBinding.inflate(inflater, container, false);
    return binding.getRoot();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    viewModel = new ViewModelProvider(this).get(MainViewModel.class);
    viewModel.getGroups().observe(getViewLifecycleOwner(), (groups) ->
        binding.recyclerView.setAdapter(new GroupAdapter(groups, getContext())));
  }
}
