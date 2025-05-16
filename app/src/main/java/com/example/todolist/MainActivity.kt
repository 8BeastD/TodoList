package com.example.todolist

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Directly launch HomeActivity
        startActivity(Intent(this, HomeActivity::class.java))
        finish() // Finish this activity so it doesn't remain in the back stack
    }
}
