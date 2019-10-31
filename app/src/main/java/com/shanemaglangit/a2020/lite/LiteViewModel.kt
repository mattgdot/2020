package com.shanemaglangit.a2020.lite

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.shanemaglangit.a2020.setAlarmManager

class LiteViewModel(application: Application) : AndroidViewModel(application) {
    private val sharedPreferences
            = application.getSharedPreferences("user_pref", Context.MODE_PRIVATE)

    private val editor = sharedPreferences.edit()

    val duration = MutableLiveData<Int>(sharedPreferences.getInt("break_duration", 20))
    val work = MutableLiveData<Int>(sharedPreferences.getInt("work_duration", 20))
    val isEnabled = MutableLiveData<Boolean>(sharedPreferences.getBoolean("break_enabled", false))

    fun savePreferences() {
        editor.putInt("break_duration", duration.value!!)
        editor.putInt("work_duration", work.value!!)
        editor.apply()

        setAlarmManager(getApplication())

        Toast
            .makeText(getApplication(), "Changes saved successfully!", Toast.LENGTH_SHORT)
            .show()
    }

    fun toggleEnabled() {
        editor.putBoolean("break_enabled", isEnabled.value!!)
        editor.apply()

        setAlarmManager(getApplication())

        Toast
            .makeText(getApplication(), "Break is now ${if(isEnabled.value!!) "enabled" else "disabled"}", Toast.LENGTH_SHORT)
            .show()
    }
}