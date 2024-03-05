package com.example.gemsfictoon.controller

import android.content.Context

class TokenManager(context:Context) {

    private val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveToken(token: String) {
        val editor = sharedPreferences.edit()
        editor.putString("token", token)
        editor.apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun clearToken() {
        val editor = sharedPreferences.edit()
        editor.remove("token")
        editor.apply()
    }
}