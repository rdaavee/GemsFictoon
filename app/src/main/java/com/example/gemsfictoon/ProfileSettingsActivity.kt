package com.example.gemsfictoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import com.example.gemsfictoon.controller.ApiClient
import com.example.gemsfictoon.controller.TokenManager
import com.example.gemsfictoon.databinding.ActivityProfileSettingsBinding
import com.example.gemsfictoon.models.LogoutResponse
import retrofit2.Call

import retrofit2.Callback
import retrofit2.Response

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

            val tokenManager = TokenManager(applicationContext)

            val token = tokenManager.getToken()

            Log.d("token", "$token")

            if(token == null){
                val intent = Intent(this, LoginAndSignupActivity::class.java)
                startActivity(intent)
                finish()
            }

            val logout = ApiClient.logout.postData("Bearer $token")

            logout.enqueue(object :Callback<LogoutResponse>{
                override fun onResponse(
                    call: Call<LogoutResponse>,
                    response: Response<LogoutResponse>
                ) {
                    Log.d("response","$response")

                    if(response.isSuccessful){
                        val state = response.body()
                        Log.d("token","$state")
                        if(state?.status == true){
                            tokenManager.clearToken()
                            val intent = Intent(this@ProfileSettingsActivity, LoginAndSignupActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }
                }

                override fun onFailure(call: Call<LogoutResponse>, t: Throwable) {
                    Log.d("Failed","$t")
                }
            })

            }

        }

    }





