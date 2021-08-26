package edu.cnm.deepdive.teamassignmentsandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
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

  private final OnGroupActionClickListener tasksListener;

  private final OnGroupActionClickListener editListener;
  private final OnGroupActionClickListener deleteListener;


  private final LayoutInflater inflater;

  /**
   * Allows for implementation of default listener interfaces.
   *  @param groups                    List of groups.
   * @param context                   interface allows access to application-specific resources and
   *                                  classes
   * @param tasksListener Helper method that passes group id to the viewholder.
   * @param editListener
   * @param deleteListener
   */
  public GroupAdapter(
      List<Group> groups, Context context,
      OnGroupActionClickListener tasksListener,
      OnGroupActionClickListener editListener,
      OnGroupActionClickListener deleteListener) {
    this.groups = groups;
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.tasksListener = tasksListener;
    this.editListener = editListener;
    this.deleteListener = deleteListener;
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
    return new Holder(binding);
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
     */
    Holder(ItemGroupBinding binding) {
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
          tasksListener.onGroupActionClick(v, group));
      if (group.isCurrentUserOwner()) {
        binding.editGroup.setVisibility(View.VISIBLE);
        binding.deleteGroup.setVisibility(View.VISIBLE);
        binding.editGroup.setOnClickListener((v) ->
            editListener.onGroupActionClick(v, group));
        binding.deleteGroup.setOnClickListener((v) ->
            deleteListener.onGroupActionClick(v, group));
      } else {
        binding.editGroup.setVisibility(View.GONE);
        binding.deleteGroup.setVisibility(View.GONE);
      }
    }

  }

  /**
   * Helper method that passes group id to the viewholder.
   */

  public interface OnGroupActionClickListener {

    void onGroupActionClick(View view, Group group);
  }


}
