package com.example.gemsfictoon

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AlertDialog
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import com.example.gemsfictoon.controller.ApiClient
import com.example.gemsfictoon.controller.TokenManager
import com.example.gemsfictoon.models.LoginRequest
import com.example.gemsfictoon.models.LoginResponse
import com.example.gemsfictoon.models.PostResponse
import com.example.gemsfictoon.models.RegisterRequest
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginAndSignupActivity : AppCompatActivity() {
    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_and_signup)

        val signUp = findViewById<TextView>(R.id.signUp)

        val logIn = findViewById<TextView>(R.id.logIn)

//        Login
        val email =findViewById<TextView>(R.id.eMail)
        val password = findViewById<TextView>(R.id.passwords)

//        Register
        val email_reg = findViewById<TextView>(R.id.eMails)
        val username_reg = findViewById<TextView>(R.id.userName)
        val usertype_reg = findViewById<Spinner>(R.id.userTypeSpinner)
        val password_reg = findViewById<TextView>(R.id.passwordss)
        val password_confimation_reg = findViewById<TextView>(R.id.passwords01)


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

            val requestBody = LoginRequest(email.text.toString(), password.text.toString())
            val postCall: Call<LoginResponse> = ApiClient.apiService.postData(requestBody)

            val tokenManager = TokenManager(applicationContext)
            postCall.enqueue(object : Callback<LoginResponse> {
                override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                    if (response.isSuccessful) {
                        // Successful login
                        val loginResponse: LoginResponse? = response.body()
                        // Access the response data as needed

                        if(loginResponse != null){
                            tokenManager.saveToken(loginResponse.authToken)
                            artDialogBuilder.setTitle("You have successfully logged in")
                            artDialogBuilder.setMessage("")
                            startActivity(Intent(this@LoginAndSignupActivity, MainActivity::class.java))
                            finish()

                        }
                    } else {
                        artDialogBuilder.setTitle("Login Failed")
                        artDialogBuilder.setMessage("${response.code()}")
                        // You can handle errors based on the response code or other conditions
                    }
                }

                override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                    // Network or unexpected error
                    artDialogBuilder.setTitle("Login Failed")
                    artDialogBuilder.setMessage("${t.message}")
                }
            })
        }

        signUpConfirm.setOnClickListener {

            Log.d("email","${email_reg.text.toString()}")
            Log.d("username","${username_reg.text.toString()}")
            Log.d("usertype","${usertype_reg.selectedItem.toString()}")
            Log.d("password","${password_reg.text.toString()}")
            Log.d("confirm password","${password_confimation_reg.text.toString()}")



            val email = email_reg.text.toString()
            val username = username_reg.text.toString()
            val usertype = renderUsertype(usertype_reg.selectedItem.toString())
            val password = password_reg.text.toString()
            val password_confirm = password_confimation_reg.text.toString()

            val requestBody = RegisterRequest(email, username, usertype, password, password_confirm)
            val registerRequest:Call<PostResponse> = ApiClient.registerRequest.postData(requestBody)

            registerRequest.enqueue(object :Callback<PostResponse>{
                override fun onResponse(
                    call: Call<PostResponse>,
                    response: Response<PostResponse>
                ) {
                    if(response.isSuccessful){
                        artDialogBuilder.setTitle("${response.body().toString()}")
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
                    }else{
                        Log.d("Failed","Something Went Wrong")
                    }
                }

                override fun onFailure(call: Call<PostResponse>, t: Throwable) {
                    Log.d("Failure","$t")
                }
            })


        }
    }

    private fun renderUsertype(usertype:String):Int{
        var type = 0;
         when(usertype){
            "Admin"->type = 1;
            "Author"->type = 2;
            "Reader"->type = 3;
        }

        return type
    }
}