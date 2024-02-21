package com.example.gemsfictoon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.ImageView
import com.example.gemsfictoon.databinding.ActivityGroupNamePrivateBinding

class GroupNamePrivateActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGroupNamePrivateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGroupNamePrivateBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_group_name_private)

        findViewById<ImageView>(R.id.iv_backBtnPrivate).setOnClickListener {
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
