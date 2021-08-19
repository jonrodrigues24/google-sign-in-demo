package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.FragmentAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.GroupAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.ActivityMainBinding;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.FragmentHomeBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import edu.cnm.deepdive.teamassignmentsandroid.service.GoogleSignInService;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

  TabLayout tabLayout;
  ViewPager2 pager2;
  GroupAdapter adapter;
  ActivityMainBinding binding;
  FragmentAdapter fragmentAdapter;

  private MainViewModel viewModel;
  private RelativeLayout relativeLayout;
  private ArrayList<Group> groups;

  /**
   * Called when the activity is starting followed by initilization including tab layout.
   * @param savedInstanceState
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());

    setContentView(binding.getRoot());

    tabLayout = findViewById(R.id.tab_layout);
    pager2 = findViewById(R.id.view_pager2);

    FragmentManager manager = getSupportFragmentManager();
    fragmentAdapter = new FragmentAdapter(manager, getLifecycle());
    binding.viewPager2.setAdapter(fragmentAdapter);

    tabLayout.addTab(tabLayout.newTab().setText("Home"));
    tabLayout.addTab(tabLayout.newTab().setText("Management"));

    tabLayout.addOnTabSelectedListener(new OnTabSelectedListener() {
      @Override
      public void onTabSelected(Tab tab) {
        pager2.setCurrentItem(tab.getPosition());
      }

      @Override
      public void onTabUnselected(Tab tab) {

      }

      @Override
      public void onTabReselected(Tab tab) {

      }
    });

    pager2.registerOnPageChangeCallback(new OnPageChangeCallback() {
      @Override
      public void onPageSelected(int position) {
        tabLayout.selectTab(tabLayout.getTabAt(position));
      }
    });
  }

  private ArrayList<Group> getGroupList() {

    ArrayList<Group> groups = new ArrayList<>();

    Group group = new Group();
    group.setName("name");
    //group.setDescription("description");

    return groups;
  }

  /**
   * Prepare the Screen's standard options menu to be displayed. This is called right before the
   * menu is shown, every time it is shown. You can use this method to efficiently enable/disable
   * items or otherwise dynamically modify the contents.
   * @param menu
   * @return
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_options, menu);
    return true;
  }

  /**
   * This hook is called whenever an item in your options menu is selected.
   * @param item
   * @return
   */
  @SuppressLint("NonConstantResourceId")
  @Override
  public boolean onOptionsItemSelected(MenuItem item) {

    boolean handled = true;
    switch (item.getItemId()) {
      case R.id.sign_out:
        logout();
        break;
      default:
        handled = super.onOptionsItemSelected(item);
    }

    return super.onOptionsItemSelected(item);
  }

  private void logout() {
    GoogleSignInService.getInstance().signOut()
        .addOnCompleteListener((ignored) -> {
          Intent intent = new Intent(this, LoginActivity.class)
              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }
}