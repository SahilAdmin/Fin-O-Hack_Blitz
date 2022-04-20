package com.admin_official.det

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class AppViewModel(application: Application): AndroidViewModel(application) {

    val dailyLimit = MutableLiveData<Int>()
    val monthlyLimit = MutableLiveData<Int>()
    val yearlyLimit = MutableLiveData<Int>()

    private var thisApplication = application

    fun updateLimits() {
        with(thisApplication.getSharedPreferences(APP_SHARED_PREFERENCES_KEY, 0)) {
            dailyLimit.value = getInt(DAILY_LIMIT_KEY, 100)
            monthlyLimit.value = getInt(MONTHLY_LIMIT_KEY, 3000)
            yearlyLimit.value = getInt(YEARLY_LIMIT_KEY, 36000)

            DAILY_LIMIT = getInt(DAILY_LIMIT_KEY, 100)
            MONTHLY_LIMIT = getInt(MONTHLY_LIMIT_KEY, 3000)
            YEARLY_LIMIT = getInt(YEARLY_LIMIT_KEY, 36000)
        }
    }
}