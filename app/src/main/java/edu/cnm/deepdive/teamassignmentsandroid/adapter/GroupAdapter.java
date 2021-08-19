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

/**
 * Adapter class that transfers group live data to the recycler view in the home fragment.
 */
public class GroupAdapter extends RecyclerView.Adapter<Holder> {


  private final List<Group> groups;
  private final Context context;

  private final OnGroupClickListener listener;

  private final LayoutInflater inflater;

  /**
   * Allows for implementation of default listener interfaces.
   * @param groups
   * @param context
   * @param listener
   */
  public GroupAdapter(
      List<Group> groups, Context context,
      OnGroupClickListener listener) {
    this.groups = groups;
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  /**
   * This method calls onCreateViewHolder to create a new RecyclerView.ViewHolder
   * @param parent
   * @param viewType
   * @return
   */
  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemGroupBinding binding = ItemGroupBinding.inflate(inflater, parent, false);
    return new Holder(binding, listener);
  }

  /**
   * Updates the RecyclerView with the item at the given position.
   * @param holder
   * @param position
   */
  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);

  }

  /**
   * Returns group size.
   * @return
   */
  @Override
  public int getItemCount() {
    return groups.size();
  }

  /**
   * Creates a viewholder for data binding by the recyclerview.
   */
  class Holder extends RecyclerView.ViewHolder implements OnClickListener {

    private final ItemGroupBinding binding;
    OnGroupClickListener listener;

    /**
     * adds onclicklistener to viewholder.
     * @param binding
     * @param listener
     */
    Holder(ItemGroupBinding binding, OnGroupClickListener listener) {
      super(binding.getRoot());
      this.binding = binding;
      this.listener = listener;
      binding.getRoot().setOnClickListener(this);
    }


    private void bind(int position) {
      Group group = groups.get(position);
      binding.groupName.setText(group.getName());
      binding.groupDescription.setText(group.getName());
      binding.getRoot().setOnClickListener(this);
    }

    /**
     * passes position of a click to the view holder.
     * @param v
     */
    @Override
    public void onClick(View v) {
      listener.onGroupClick(v, groups.get(getBindingAdapterPosition()).getId());
    }
  }

  /**
   * Helper method that passes group id to the viewholder.
   */
  public interface OnGroupClickListener {
    void onGroupClick(View view, long groupId);
  }



}
