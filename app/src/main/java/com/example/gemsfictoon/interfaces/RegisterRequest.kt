package com.example.gemsfictoon.interfaces

import com.example.gemsfictoon.models.PostResponse
import com.example.gemsfictoon.models.RegisterRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface RegisterRequest
{
    @POST("mobile-register")
    fun postData(@Body requestBody: RegisterRequest):Call<PostResponse>
}