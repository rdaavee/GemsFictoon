package com.example.gemsfictoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val getStartedBtn = findViewById<Button>(R.id.btnGetStarted)

        getStartedBtn.setOnClickListener {
            startActivity(Intent(this@WelcomeActivity, LoginAndSignupActivity::class.java))
        }
    }
}