//---| Preferences/Status Storage File |---\\
package com.aspera.space

import android.content.Context

// Set + Forget --> PARK_ ===== set and was.

object ParkOnboarding {
//private const for eff and flp. no need tech
    // i guess this isn't exactly prefs, its just onboarding is done, or not.
    private const val ONBOARD_BOOLEAN_FILE = "Aspera_Onboard_Storage"
    private const val KEY_ONBOARDING_DONE = "onboarding_completed"


    fun setCompleted(context: Context, completed: Boolean) {
        val prefsDone = context.getSharedPreferences(ONBOARD_BOOLEAN_FILE, Context.MODE_PRIVATE)
        prefsDone.edit().putBoolean(KEY_ONBOARDING_DONE, completed).apply()
    }

    fun wasCompleted(context: Context): Boolean {
        val prefsDone = context.getSharedPreferences(ONBOARD_BOOLEAN_FILE, Context.MODE_PRIVATE)
        val completionKey = prefsDone.getBoolean(KEY_ONBOARDING_DONE, false)
        println("Aspera Onboarding Completed ==-->'$completionKey'")
        return completionKey

    }

}