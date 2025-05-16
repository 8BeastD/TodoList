package com.example.todolist

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi

class SnoozeReceiver : BroadcastReceiver() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onReceive(context: Context, intent: Intent) {
        val taskTitle = intent.getStringExtra("taskTitle") ?: "Reminder!"

        val snoozeTime = System.currentTimeMillis() + 5 * 60 * 1000 // 5 minutes
        AlarmScheduler.scheduleAlarm(context, 999, taskTitle, snoozeTime)
        Toast.makeText(context, "Snoozed for 5 mins", Toast.LENGTH_SHORT).show()
    }
}
