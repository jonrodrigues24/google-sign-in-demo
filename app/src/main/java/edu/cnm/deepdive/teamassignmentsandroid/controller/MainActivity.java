package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import androidx.viewpager2.widget.ViewPager2;
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener;
import com.google.android.material.tabs.TabLayout.Tab;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.FragmentAdapter;
import edu.cnm.deepdive.teamassignmentsandroid.service.GoogleSignInService;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

public class MainActivity extends AppCompatActivity {

  TabLayout tabLayout;
  ViewPager2 pager2;
  FragmentAdapter adapter;

  private MainViewModel viewModel;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    tabLayout = findViewById(R.id.tab_layout);
    pager2 = findViewById(R.id.view_pager2);

    FragmentManager manager = getSupportFragmentManager();
    adapter = new FragmentAdapter(manager, getLifecycle());
    pager2.setAdapter(adapter);

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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
 //   super.onCreateOptionsMenu(menu);
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.main_options, menu);
    return true;
  }

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