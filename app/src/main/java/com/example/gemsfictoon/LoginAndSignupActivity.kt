package com.example.gemsfictoon

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.navigation.Navigation.findNavController
import com.google.android.material.bottomnavigation.BottomNavigationView

class LoginAndSignupActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_and_signup)


        val signUp = findViewById<TextView>(R.id.signUp)
        val logIn = findViewById<TextView>(R.id.logIn)
        val loginConfirm = findViewById<Button>(R.id.loginConfirm)
        val signUpConfirm = findViewById<Button>(R.id.signUpConfirm)
        val signUpLayout = findViewById<LinearLayout>(R.id.signUpLayout)
        val logInLayout = findViewById<LinearLayout>(R.id.logInLayout)

        val artDialogBuilder = AlertDialog.Builder(this)

        signUp.setOnClickListener {
            signUp.background = resources.getDrawable(R.drawable.switch_trcks,null)
            signUp.setTextColor(resources.getColor(R.color.textColor,null))
            logIn.background = null
            signUpLayout.visibility = View.VISIBLE
            logInLayout.visibility = View.GONE
            logIn.setTextColor(resources.getColor(R.color.blueColor,null))
        }
        logIn.setOnClickListener {
            signUp.background = null
            signUp.setTextColor(resources.getColor(R.color.blueColor,null))
            logIn.background = resources.getDrawable(R.drawable.switch_trcks,null)
            signUpLayout.visibility = View.GONE
            logInLayout.visibility = View.VISIBLE
            logIn.setTextColor(resources.getColor(R.color.textColor,null))
        }

        loginConfirm.setOnClickListener {
            startActivity(Intent(this@LoginAndSignupActivity, MainActivity::class.java))
        }

        signUpConfirm.setOnClickListener {
            artDialogBuilder.setTitle("You have successfully registered")
            artDialogBuilder.setMessage("")
            artDialogBuilder.setCancelable(false)
            artDialogBuilder.setPositiveButton("Proceed") { _, _ ->
                startActivity(Intent(this@LoginAndSignupActivity, LoginAndSignupActivity::class.java))
                finish()
            }
            artDialogBuilder.setNegativeButton("No") { _, _ ->
            }
            val alertDialogBox = artDialogBuilder.create()
            alertDialogBox.show()
        }
    }
}