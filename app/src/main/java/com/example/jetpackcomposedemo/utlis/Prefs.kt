package com.example.jetpackcomposedemo.utlis

import android.content.Context
import android.content.SharedPreferences

class Prefs(context: Context) {

    private val preferences: SharedPreferences =
        context.getSharedPreferences("MAIN", Context.MODE_PRIVATE)

    var themeDark: Boolean
        get() = preferences.getBoolean("themeDark", false)
        set(value) = preferences.edit().putBoolean("themeDark", value).apply()
}