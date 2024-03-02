package com.example.gemsfictoon.controller

import com.example.gemsfictoon.constants.BaseRoute
import com.example.gemsfictoon.interfaces.AuthCheck
import com.example.gemsfictoon.interfaces.LogoutRequest
import com.example.gemsfictoon.interfaces.PostRequest
import com.example.gemsfictoon.interfaces.UserProfileRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object ApiClient {


    private val retrofit = Retrofit.Builder()
        .baseUrl(BaseRoute.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: PostRequest = retrofit.create(PostRequest::class.java)

    val checkAuth: AuthCheck = retrofit.create(AuthCheck::class.java)

    val userProfile: UserProfileRequest = retrofit.create(UserProfileRequest::class.java)

    val logout: LogoutRequest = retrofit.create(LogoutRequest::class.java)
}