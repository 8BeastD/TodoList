package com.example.todolist

import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.util.Calendar

class HomeActivity : AppCompatActivity() {

    private lateinit var taskInput: EditText
    private lateinit var addButton: Button
    private lateinit var taskRecycler: RecyclerView
    private val tasks = mutableListOf<Task>()
    private val adapter = TaskAdapter(tasks)

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "TaskReminderChannel"
            val descriptionText = "Channel for task reminders"
            val importance = NotificationManager.IMPORTANCE_HIGH
            val channel = NotificationChannel("task_reminder", name, importance).apply {
                description = descriptionText
            }
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        taskInput = findViewById(R.id.taskInput)
        addButton = findViewById(R.id.addButton)
        taskRecycler = findViewById(R.id.taskRecycler)

        taskRecycler.layoutManager = LinearLayoutManager(this)
        taskRecycler.adapter = adapter

        addButton.setOnClickListener {
            val taskText = taskInput.text.toString().trim()
            if (taskText.isNotEmpty()) {
                val calendar = Calendar.getInstance()
                DatePickerDialog(this, { _, year, month, day ->
                    TimePickerDialog(this, { _, hour, minute ->

                        calendar.set(year, month, day, hour, minute, 0)

                        val date = String.format("%02d-%02d-%d", day, month + 1, year)
                        val time = String.format("%02d:%02d", hour, minute)

                        val task = Task(taskText, date, time)
                        tasks.add(task)
                        adapter.notifyItemInserted(tasks.size - 1)
                        taskInput.text.clear()

                        // ✅ Schedule the alarm
                        AlarmScheduler.scheduleAlarm(this, tasks.size, task.title, calendar.timeInMillis)

                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show()
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show()
                }
        }



    }



}

// Task.kt

data class Task(
    var title: String,
    var date: String = "",
    var time: String = "",  // ✅ Capital "S" in String
    var isDone: Boolean = false
)




