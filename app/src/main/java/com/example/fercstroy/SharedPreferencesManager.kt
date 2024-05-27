package com.example.fercstroy

import android.content.Context

class SharedPreferencesManager(private val context: Context) {

    companion object{
        private const val USER_ID = "user_id"
        private const val USER_DATA = "user_data"
    }

    fun saveUserID(context: Context, userID: Int) {
        val sharedPreferences = context.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID, userID.toString())
        editor.apply()
    }

    fun getUserID(context: Context): Int? {
        val sharedPreferences = context.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE)
        return sharedPreferences.getString(USER_ID, null)?.toInt()
    }

    fun logout(context: Context) {
        val sharedPreferences = context.getSharedPreferences(USER_DATA, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.clear().apply()
    }

}