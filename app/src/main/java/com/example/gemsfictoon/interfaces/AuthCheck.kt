package com.example.gemsfictoon.interfaces

import com.example.gemsfictoon.models.AuthCheckResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface AuthCheck {
    @GET("auth-check")
    fun getData(@Header("Authorization") token:String):Call<AuthCheckResponse>
}