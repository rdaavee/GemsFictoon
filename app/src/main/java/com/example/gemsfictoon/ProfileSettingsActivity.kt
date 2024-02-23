package com.example.gemsfictoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.example.gemsfictoon.databinding.ActivityProfileSettingsBinding

class ProfileSettingsActivity : AppCompatActivity() {

    private lateinit var binding : ActivityProfileSettingsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileSettingsBinding.inflate(layoutInflater)

        setContentView(R.layout.activity_profile_settings)

        findViewById<ImageView>(R.id.iv_settingsBackBtn).setOnClickListener {
                finish()
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
            if (savedInstanceState == null) {
                val transaction = supportFragmentManager.beginTransaction()
                transaction.addToBackStack(null)
                transaction.commit()
            }
        }

        findViewById<TextView>(R.id.tv_web).setOnClickListener {
            // I don't know what do you mean by web here
        }

        findViewById<TextView>(R.id.tv_aboutApp).setOnClickListener {

        }

        findViewById<TextView>(R.id.tv_helpSupport).setOnClickListener {

        }

        findViewById<TextView>(R.id.tv_logout).setOnClickListener {
            val intent = Intent(this, LoginAndSignupActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}