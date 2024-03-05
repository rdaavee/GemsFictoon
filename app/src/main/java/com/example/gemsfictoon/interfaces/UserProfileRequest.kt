package com.example.gemsfictoon.interfaces

import com.example.gemsfictoon.models.ProfileResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserProfileRequest {
    @GET("profile")
    fun getData(@Header("Authorization") token: String):Call<ProfileResponse>
}