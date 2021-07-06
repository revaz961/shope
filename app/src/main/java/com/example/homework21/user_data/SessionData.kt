package com.example.homework21.user_data

import android.content.Context
import android.content.SharedPreferences
import com.example.homework21.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionData @Inject constructor(@ApplicationContext private val context: Context) {
    companion object{
        private const val SESSION = "SESSION"
        private const val TOKEN = "TOKEN"
    }
    private val sharedPreferences:SharedPreferences by lazy{
        context.getSharedPreferences(context.getString(R.string.session),Context.MODE_PRIVATE)
    }

    fun saveSession(session: Boolean) {
        sharedPreferences.edit().putBoolean(SESSION, session).apply()
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString(TOKEN, token).apply()
    }

    fun hasSession() = sharedPreferences.getBoolean(SESSION, false)
}