package com.example.todolist

// TaskAdapter.kt

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class TaskAdapter(private val taskList: MutableList<Task>) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {

    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val taskText: TextView = itemView.findViewById(R.id.taskText)
        val deleteButton: Button = itemView.findViewById(R.id.deleteButton)
        val dateText: TextView = itemView.findViewById(R.id.dateText)
        val timeText: TextView = itemView.findViewById(R.id.timeText)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = taskList[position]
        holder.taskText.text = task.title
        holder.dateText.text = task.date
        holder.timeText.text = task.time

        holder.taskText.paint.isStrikeThruText = task.isDone

        holder.taskText.setOnClickListener {
            task.isDone = !task.isDone
            notifyItemChanged(position)
        }

        holder.deleteButton.setOnClickListener {
            taskList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun getItemCount(): Int = taskList.size
}
