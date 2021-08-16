package edu.cnm.deepdive.teamassignmentsandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.GroupAdapter.Holder;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.ItemGroupBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import java.util.List;
import org.jetbrains.annotations.NotNull;

public class GroupAdapter extends RecyclerView.Adapter<Holder> {

  private final List<Group> groups;
  private Context context;
  private final LayoutInflater inflater;

  public GroupAdapter(
      List<Group> groups, Context context) {
    this.groups = groups;
    this.context = context;
    inflater = LayoutInflater.from(context);
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemGroupBinding binding = ItemGroupBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);

  }

  @Override
  public int getItemCount() {
    return groups.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemGroupBinding binding;

    Holder(ItemGroupBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
      binding.getRoot();
    }

    private void bind(int position) {
      Group group = groups.get(position);
      binding.groupName.setText(group.getName());
      binding.groupDescription.setText(group.getName()); //create group description methods
      binding.getRoot();
    }
  }
}
