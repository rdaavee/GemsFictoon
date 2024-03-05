package com.example.gemsfictoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import com.example.gemsfictoon.controller.ApiClient
import com.example.gemsfictoon.controller.TokenManager
import com.example.gemsfictoon.models.AuthCheckResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        val getStartedBtn = findViewById<Button>(R.id.btnGetStarted)

        val tokenManager = TokenManager(applicationContext)


        getStartedBtn.setOnClickListener {
            val token = tokenManager.getToken()
            
            val authCheckCall = ApiClient.checkAuth.getData("Bearer $token")

            authCheckCall.enqueue(object : Callback<AuthCheckResponse>{
                override fun onResponse(
                    call: Call<AuthCheckResponse>,
                    response: Response<AuthCheckResponse>
                ) {
                    if (response.isSuccessful){
                        val authCheckResponse:AuthCheckResponse? = response.body()
                        Log.d("Response Success",authCheckResponse?.status.toString())
                        if(authCheckResponse?.status == true){
                            startActivity(Intent(this@WelcomeActivity, MainActivity::class.java))
                        }
                    }else{
                        startActivity(Intent(this@WelcomeActivity, LoginAndSignupActivity::class.java))
                        Log.d("Response Unsuccessful","Something Went Wrong")
                    }
                }

                override fun onFailure(call: Call<AuthCheckResponse>, t: Throwable) {
                    startActivity(Intent(this@WelcomeActivity, LoginAndSignupActivity::class.java))

                    Log.d("Response Failure","Something Went Wrong: $t")
                }
            })
            finish()
        }
    }
}