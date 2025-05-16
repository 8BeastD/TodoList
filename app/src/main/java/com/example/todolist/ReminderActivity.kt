package com.example.todolist

import android.media.RingtoneManager
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class ReminderActivity : AppCompatActivity() {
    private var ringtone: android.media.Ringtone? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder)

        val taskTitle = intent.getStringExtra("taskTitle") ?: "Reminder!"
        findViewById<TextView>(R.id.taskTitleTextView).text = taskTitle

        // Alarm Sound
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(this, alarmUri)
        ringtone?.play()

        findViewById<Button>(R.id.dismissButton).setOnClickListener {
            ringtone?.stop()
            finish()
            }
        }
}
