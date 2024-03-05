package com.example.gemsfictoon.interfaces

import com.example.gemsfictoon.models.LoginRequest
import com.example.gemsfictoon.models.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST


interface PostRequest {
    @POST("login")
    fun postData(@Body requestBody: LoginRequest):Call<LoginResponse>
}