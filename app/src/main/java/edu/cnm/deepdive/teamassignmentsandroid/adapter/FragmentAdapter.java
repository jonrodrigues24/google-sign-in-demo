package edu.cnm.deepdive.teamassignmentsandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.controller.HomeFragment;
import edu.cnm.deepdive.teamassignmentsandroid.controller.ManagementFragment;
import org.jetbrains.annotations.NotNull;

public class FragmentAdapter extends FragmentStateAdapter {

  public FragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager,
      @NonNull @NotNull Lifecycle lifecycle) {
    super(fragmentManager, lifecycle);
  }

  @NonNull
  @NotNull
  @Override
  public Fragment createFragment(int position) {

    if (position == 1) {
      return new ManagementFragment();
    } else {
      return new HomeFragment();
    }
  }

  @Override
  public int getItemCount() {
    return 2;
  }
}
