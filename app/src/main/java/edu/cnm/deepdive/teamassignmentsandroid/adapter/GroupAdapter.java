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

  private final OnGroupTasksClickListener onGroupTasksClickListener;

  private final OnGroupEditClickListener onGroupEditClickListener;

  private final LayoutInflater inflater;

  /**
   * Allows for implementation of default listener interfaces.
   *
   * @param groups                    List of groups.
   * @param context                   interface allows access to application-specific resources and
   *                                  classes
   * @param onGroupTasksClickListener Helper method that passes group id to the viewholder.
   * @param onGroupEditClickListener
   */
  public GroupAdapter(
      List<Group> groups, Context context,
      OnGroupTasksClickListener onGroupTasksClickListener,
      OnGroupEditClickListener onGroupEditClickListener) {
    this.groups = groups;
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.onGroupTasksClickListener = onGroupTasksClickListener;
    this.onGroupEditClickListener = onGroupEditClickListener;
  }

  /**
   * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an
   * item.
   *
   * @param parent   The view group is the base class for layouts and views containers
   * @param viewType
   * @return returns binding holder.
   */
  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemGroupBinding binding = ItemGroupBinding.inflate(inflater, parent, false);
    return new Holder(binding, onGroupTasksClickListener);
  }

  /**
   * Called by RecyclerView to display the data at the specified position.
   *
   * @param holder   Creates a viewholder for data binding by the recyclerview
   * @param position
   */
  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);

  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   *
   * @return group size.
   */
  @Override
  public int getItemCount() {
    return groups.size();
  }

  /**
   * Creates a viewholder for data binding by the recyclerview.
   */
  class Holder extends RecyclerView.ViewHolder {

    private final ItemGroupBinding binding;

    /**
     * adds onclicklistener to viewholder.
     *
     * @param binding  A type which binds the views in a layout XML to fields
     * @param listener Helper method that passes group id to the viewholder.
     */
    Holder(ItemGroupBinding binding, OnGroupTasksClickListener listener) {
      super(binding.getRoot());
      this.binding = binding;
    }

    /**
     * bind - connects current instance to view holder.
     *
     * @param position
     */

    private void bind(int position) {
      Group group = groups.get(position);
      binding.groupName.setText(group.getName());
      binding.groupTasks.setOnClickListener((v) ->
          onGroupTasksClickListener.onGroupTasksClick(binding.groupTasks, group.getId()));
      binding.editGroup.setOnClickListener((v) ->
          onGroupEditClickListener.onGroupEditClick(binding.groupTasks, group.getId()));
    }

  }

  /**
   * Helper method that passes group id to the viewholder.
   */
  public interface OnGroupTasksClickListener {

    void onGroupTasksClick(View view, long groupId);
  }

  public interface OnGroupEditClickListener {

    void onGroupEditClick(View view, long groupId);
  }


}
