package com.example.finalassignment

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

// Main activity that serves as the entry point of the application
class MainActivity : AppCompatActivity() {

    // Called when the activity is created (initialization)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)  // Sets the layout for this activity

        // Automatically navigate to LoginActivity when this activity starts
        val intent = Intent(this, LoginActivity::class.java)  // Create an intent to launch LoginActivity
        startActivity(intent)  // Start the LoginActivity

    }

    }
