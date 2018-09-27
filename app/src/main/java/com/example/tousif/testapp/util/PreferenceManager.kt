package com.example.tousif.testapp.util

import android.content.Context

object PreferenceManager {
    private val SHAREDPREFERENCE_FILE_NAME = "login_status"
    private val SHAREDPREFERENCE_LOGIN_KEY = "loggedIn"

    fun writetoSharedPreference(context : Context,loggedIn : Boolean){
        val sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putBoolean(SHAREDPREFERENCE_LOGIN_KEY, loggedIn)
        editor.apply()
    }

    fun readFromSharedPreference(context: Context):Boolean{
        val sharedPreferences = context.getSharedPreferences(SHAREDPREFERENCE_FILE_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(SHAREDPREFERENCE_LOGIN_KEY, false)
    }
}