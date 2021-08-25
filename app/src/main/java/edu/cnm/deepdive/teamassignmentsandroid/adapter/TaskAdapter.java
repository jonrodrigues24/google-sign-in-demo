package edu.cnm.deepdive.teamassignmentsandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import edu.cnm.deepdive.teamassignmentsandroid.adapter.TaskAdapter.Holder;
import edu.cnm.deepdive.teamassignmentsandroid.databinding.ItemTaskBinding;
import edu.cnm.deepdive.teamassignmentsandroid.model.pojo.Task;
import java.text.DateFormat;
import java.util.List;

/**
 * Adapter class that transfers task live data to the recycler view in the home fragment.
 */
public class TaskAdapter extends RecyclerView.Adapter<Holder> {

  private final List<Task> tasks;
  private final Context context;
  private final LayoutInflater inflater;
  private final OnTaskClickListener editTaskListener;
  private final OnTaskClickListener deleteTaskListener;

  private final DateFormat dateFormat;

  /**
   * @param tasks              will populate list
   * @param context            is the source context which contains the existing shared preferences
   * @param editTaskListener   passes task id to holder
   * @param deleteTaskListener
   */
  public TaskAdapter(List<Task> tasks, Context context, OnTaskClickListener editTaskListener,
      OnTaskClickListener deleteTaskListener) {
    this.tasks = tasks;
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.editTaskListener = editTaskListener;
    dateFormat = android.text.format.DateFormat.getDateFormat(context);
    this.deleteTaskListener = deleteTaskListener;
  }

  /**
   * Called when RecyclerView needs a new RecyclerView.ViewHolder of the given type to represent an
   * item.
   *
   * @param parent   The view group is the base class for layouts and views containers
   * @param viewType default implementation of this method returns 0, making the assumption of a
   *                 single view type for the adapter
   * @return returns binding holder.
   */
  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemTaskBinding binding = ItemTaskBinding.inflate(inflater, parent, false);
    return new Holder(binding);
  }

  /**
   * Called by RecyclerView to display the data at the specified position.
   *
   * @param holder   Creates a viewholder for data binding by the recyclerview
   * @param position will reflect the item at the given position
   */
  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  /**
   * Returns the total number of items in the data set held by the adapter.
   *
   * @return task size.
   */
  @Override
  public int getItemCount() {
    return tasks.size();
  }

  class Holder extends RecyclerView.ViewHolder {

    private final ItemTaskBinding binding;
    private Task task;


    /**
     * adds onclicklistener to viewholder.
     *
     * @param binding A type which binds the views in a layout XML to fields
     */
    Holder(ItemTaskBinding binding) {
      super(binding.getRoot());
      this.binding = binding;
    }

    /**
     * bind - connects current instance to view holder.
     *
     * @param position is size of list of task
     */
    private void bind(int position) {
      task = tasks.get(position);
      binding.taskTitle.setText(task.getTitle());
      if (task.getDescription() != null && !task.getDescription().isEmpty()) {
        binding.taskDescription.setText(task.getDescription());
        binding.taskDescription.setVisibility(View.VISIBLE);
      } else {
        binding.taskDescription.setVisibility(View.GONE);
      }
      if (task.getDueDate() != null) {
        binding.dueDate.setText(dateFormat.format(task.getDueDate()));
        binding.dueDate.setVisibility(View.VISIBLE);
      } else {
        binding.dueDate.setVisibility(View.GONE);
      }
      binding.editTask.setOnClickListener((v) -> editTaskListener.onTaskClick(v, task.getId()));
      binding.deleteTask.setOnClickListener((v) -> deleteTaskListener.onTaskClick(v, task.getId()));
    }
  }

  /**
   * Helper method that passes task id to the viewholder.
   */
  public interface OnTaskClickListener {

    void onTaskClick(View view, long taskId);
  }

}