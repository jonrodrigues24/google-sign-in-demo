package edu.cnm.deepdive.teamassignmentsandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import edu.cnm.deepdive.teamassignmentsandroid.R;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.ItemMemberBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.User;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class GroupMemberAdapter extends ArrayAdapter<User> {

  private final Context context;
  private final Set<User> members = new HashSet<>();
  private final LayoutInflater inflater;
  private final OnUserCheckedListener listener;

  public GroupMemberAdapter(@NonNull Context context,
      OnUserCheckedListener listener) {
    super(context, R.layout.item_member, new LinkedList<>());
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @NonNull
  @Override
  public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
    ItemMemberBinding binding = (convertView != null)
        ? ItemMemberBinding.bind(convertView)
        : ItemMemberBinding.inflate(inflater, parent, false);
    User user = getItem(position);
    binding.displayName.setText(user.getDisplayName());
    binding.membership.setChecked(members.contains(user));
    binding.membership.setOnCheckedChangeListener(
        (btn, checked) -> listener.onUserChecked(btn, user, checked));
    return binding.getRoot();
  }

  public Set<User> getMembers() {
    return members;
  }

  public interface OnUserCheckedListener {

    void onUserChecked(View view, User user, boolean checked);
  }

}
