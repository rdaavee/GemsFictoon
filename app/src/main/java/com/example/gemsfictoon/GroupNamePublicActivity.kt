package com.example.gemsfictoon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import com.example.gemsfictoon.databinding.ActivityGroupNamePublicBinding

class GroupNamePublicActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupNamePublicBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupNamePublicBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_group_name_public)

        findViewById<ImageView>(R.id.iv_backBtnPublic).setOnClickListener {
            if (supportFragmentManager.backStackEntryCount > 0) {
                supportFragmentManager.popBackStack()
            } else {
                finish()
            }
        }

        if (savedInstanceState == null) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }
}
