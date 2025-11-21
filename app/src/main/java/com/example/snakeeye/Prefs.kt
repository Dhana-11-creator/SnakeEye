package com.example.snakeeye

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit

class Prefs(ctx: Context) {
    private val sp: SharedPreferences = ctx.getSharedPreferences("snake_prefs", Context.MODE_PRIVATE)
    fun isOnboardingSeen() = sp.getBoolean("onboarding_seen", false)
    fun setOnboardingSeen(seen: Boolean) { sp.edit { putBoolean("onboarding_seen", seen) } }
}
