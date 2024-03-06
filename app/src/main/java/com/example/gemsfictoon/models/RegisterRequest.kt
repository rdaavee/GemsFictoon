package com.example.gemsfictoon.models

data class RegisterRequest(
    val email:String,
    val username:String,
    val usertype:Int,
    val password:String,
    val password_confirmation:String
)
