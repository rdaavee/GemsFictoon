package com.example.gemsfictoon.interfaces

import com.example.gemsfictoon.models.LoginResponse
import com.example.gemsfictoon.models.LogoutResponse
import retrofit2.Call
import retrofit2.http.Header
import retrofit2.http.POST

interface LogoutRequest {
    @POST("logout")
    fun postData(@Header("Authorization") token: String):Call<LogoutResponse>
}