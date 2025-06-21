package com.example.notespreparation

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class NotificationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notification)

        val textView: TextView = findViewById(R.id.notificationTextView)

        // Get the notification message from the Intent extra
        val message = intent.getStringExtra("message")
        textView.text = message
    }
}