package com.demo.clean.framework.sharedpreferences

import android.content.SharedPreferences

class PreferencesManager constructor(private val preferences: SharedPreferences) {

    companion object{
      const val  PREFERENCES_NAME = "SP"
    }
    private val KEY_NAME = "KEY_NAME"

    fun getName(): String {
        return preferences.getString(KEY_NAME, "")!!
    }

    fun setName(value: String) {
        preferences.edit().putString(KEY_NAME, value).apply()
    }
}