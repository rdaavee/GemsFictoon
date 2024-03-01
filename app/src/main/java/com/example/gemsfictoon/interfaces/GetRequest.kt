package com.example.gemsfictoon.interfaces
import com.example.gemsfictoon.models.LoginResponse
import retrofit2.Call
import retrofit2.http.GET


interface GetRequest {
    @GET("end")
fun getData():Call<LoginResponse>
}