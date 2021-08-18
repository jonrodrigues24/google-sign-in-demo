package edu.cnm.deepdive.teamassignmentsandroid.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.controller.HomeFragment;
import edu.cnm.deepdive.teamassignmentsandroid.controller.ManagementFragment;
import org.jetbrains.annotations.NotNull;

/**
 * Implementation of pager adapter that represents each page as a fragment that is persistently
 * kept in the fragment manager as long as the user can return to the page.
 */
public class FragmentAdapter extends FragmentStateAdapter {

  /**
   * Constructor for Fragment adapter class.
   * @param fragmentManager
   * @param lifecycle
   */
  public FragmentAdapter(@NonNull @NotNull FragmentManager fragmentManager,
      @NonNull @NotNull Lifecycle lifecycle) {
    super(fragmentManager, lifecycle);
  }

  /**
   * Stores positional information for tab navigation.
   * @param position
   * @return
   */
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

  /**
   * Stores number of tabs in tab navigation.
   * @return
   */
  @Override
  public int getItemCount() {
    return 2;
  }
}
