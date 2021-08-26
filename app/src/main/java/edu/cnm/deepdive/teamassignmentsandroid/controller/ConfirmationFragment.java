package edu.cnm.deepdive.teamassignmentsandroid.controller;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import java.io.Serializable;

public class ConfirmationFragment extends DialogFragment {

  private long id;
  private String subject;
  private Action action;

  @Override
  public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Bundle args = getArguments();
    if (args != null) {
      ConfirmationFragmentArgs confirmationFragmentArgs = ConfirmationFragmentArgs.fromBundle(args);
      id = confirmationFragmentArgs.getId();
      subject = confirmationFragmentArgs.getSubject();
      action = (Action) confirmationFragmentArgs.getAction();
    }
  }

  @NonNull
  @Override
  public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
   return new AlertDialog.Builder(getContext())
       .setTitle(R.string.confirmation_title)
       .setIcon(android.R.drawable.ic_dialog_alert)
       .setMessage(getString(R.string.delete_message, subject))
       .setPositiveButton(android.R.string.yes, (d, w) -> {
         action.execute();
       })
       .setNegativeButton(android.R.string.no, null)
       .create();
  }

  public interface Action extends Serializable {
    void execute();
  }

}
