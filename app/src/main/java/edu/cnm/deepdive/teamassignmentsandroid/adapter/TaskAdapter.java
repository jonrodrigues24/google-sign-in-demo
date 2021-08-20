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
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<Holder> {

  private final List<Task> tasks;
  private final Context context;
  private final LayoutInflater inflater;
  private final OnTaskClickListener listener;

  public TaskAdapter(List<Task> tasks, Context context, OnTaskClickListener listener) {
    this.tasks = tasks;
    this.context = context;
    inflater = LayoutInflater.from(context);
    this.listener = listener;
  }

  @NonNull
  @Override
  public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    ItemTaskBinding binding = ItemTaskBinding.inflate(inflater, parent, false);
    return new Holder(binding, listener);
  }

  @Override
  public void onBindViewHolder(@NonNull Holder holder, int position) {
    holder.bind(position);
  }

  @Override
  public int getItemCount() {
    return tasks.size();
  }

  class Holder extends RecyclerView.ViewHolder implements OnClickListener {

    private final ItemTaskBinding binding;
    OnTaskClickListener listener;

    Holder(ItemTaskBinding binding, OnTaskClickListener listener) {
      super(binding.getRoot());
      this.binding = binding;
      this.listener = listener;
      binding.getRoot().setOnClickListener(this);
    }

    private void bind(int position) {
      Task task = tasks.get(position);
      binding.taskTitle.setText(task.getTitle());
      binding.taskDescription.setText(task.getDescription());
      binding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
      listener.onTaskClick(v, tasks.get(getBindingAdapterPosition()).getId());
    }
  }

  public interface OnTaskClickListener {

    void onTaskClick(View view, long taskId);
  }

}