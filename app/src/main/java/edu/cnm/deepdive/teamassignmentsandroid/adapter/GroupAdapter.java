package edu.cnm.deepdive.teamassignmentsandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.GroupAdapter.Holder;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.ItemGroupBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Group;
import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<Holder> {


  private final List<Group> groups;
  private final Context context;

  private final OnGroupClickListener listener;

  private final LayoutInflater inflater;

  public GroupAdapter(
      List<Group> groups, Context context,
      OnGroupClickListener listener) {
    this.groups = groups;
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemGroupBinding binding = ItemGroupBinding.inflate(inflater, parent, false);
    return new Holder(binding, listener);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);

  }

  @Override
  public int getItemCount() {
    return groups.size();
  }

  class Holder extends RecyclerView.ViewHolder implements OnClickListener {

    private final ItemGroupBinding binding;
    OnGroupClickListener listener;

    Holder(ItemGroupBinding binding, OnGroupClickListener listener) {
      super(binding.getRoot());
      this.binding = binding;
      this.listener = listener;
      binding.getRoot().setOnClickListener(this);
    }

    private void bind(int position) {
      Group group = groups.get(position);
      binding.groupName.setText(group.getName());
      binding.groupDescription.setText(group.getName()); //create group description methods
//      binding.getRoot().setOnClickListener((v) -> listener.onGroupClick(v, position, group));
      binding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      listener.onGroupClick(v, groups.get(getBindingAdapterPosition()).getId());
    }
  }

/*  public interface OnGroupClickListener {
    void onGroupClick(View view, int position, Group group);
  }*/

  public interface OnGroupClickListener {
    void onGroupClick(View view, long groupId);
  }



}
