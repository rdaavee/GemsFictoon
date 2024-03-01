package com.example.gemsfictoon.controller

import com.example.gemsfictoon.interfaces.PostRequest
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    private const val BASE_URL = "http://192.168.100.244:8000/api/" // Replace with your base URL

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val apiService: PostRequest = retrofit.create(PostRequest::class.java)
}