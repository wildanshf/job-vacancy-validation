package com.capstone.jobvacancyvalidation.data

import android.content.Context
import android.content.SharedPreferences

class UserPreferences (context: Context){
    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val editor: SharedPreferences.Editor = preferences.edit()

    fun setToken(value: String){
        editor.putString(TOKEN, value)
        editor.apply()
    }

    fun getToken(): String {
        return if (preferences.getString(TOKEN, "") != null) preferences.getString(
            TOKEN,
            ""
        )!! else ""
    }

    fun clearPreference() {
        editor.clear()
        editor.apply()
    }

    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val TOKEN = "token"
    }
}