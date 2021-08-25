package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.ActivityMainBinding;
import edu.cnm.deepdive.teamassignmentsandroid.service.GoogleSignInService;
import edu.cnm.deepdive.teamassignmentsandroid.viewmodel.MainViewModel;

/**
 * Called when activity is started.  Contains the tab layout binding.
 */
public class MainActivity extends AppCompatActivity {

  ActivityMainBinding binding;

  private MainViewModel viewModel;
  private AppBarConfiguration appBarConfiguration;
  private NavController navController;


  /**
   * Called when the activity is starting followed by initilization including tab layout.
   *
   * @param savedInstanceState A mapping from String keys to various Parcelable values
   */
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    binding = ActivityMainBinding.inflate(getLayoutInflater());

    setContentView(binding.getRoot());

    appBarConfiguration = new AppBarConfiguration.Builder(
        R.id.groups_fragment, R.id.tasks_fragment
    )
        .build();
    navController = Navigation.findNavController(this, R.id.nav_host_fragment);
    NavigationUI.setupActionBarWithNavController(this, navController);
    //NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);

  }

  /**
   * Prepare the Screen's standard options menu to be displayed. This is called right before the
   * menu is shown, every time it is shown. You can use this method to efficiently enable/disable
   * items or otherwise dynamically modify the contents.
   *
   * @param menu Interface for managing the items in a menu
   * @return shows user the option to sign out
   */
  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main_options, menu);
    return true;
  }

  /**
   * This hook is called whenever an item in your options menu is selected.
   *
   * @param item Interface for direct access to a previously created menu item
   * @return users choice to sign out is granted on click
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

  @Override
  public boolean onSupportNavigateUp() {
    return NavigationUI.navigateUp(navController, appBarConfiguration)
        || super.onSupportNavigateUp();
  }

  /**
   * Log out of application and return to the google sign in menu.
   */
  private void logout() {
    GoogleSignInService.getInstance().signOut()
        .addOnCompleteListener((ignored) -> {
          Intent intent = new Intent(this, LoginActivity.class)
              .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
          startActivity(intent);
        });
  }
}