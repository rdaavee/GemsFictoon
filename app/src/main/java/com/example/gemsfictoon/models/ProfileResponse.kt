package com.example.gemsfictoon.models

data class ProfileResponse(
    val id:Int,
    val username:String,
    val email:String,
    val usertype:Int,
    val biography:String,
    val follower:Int,
    val following:Int
)
