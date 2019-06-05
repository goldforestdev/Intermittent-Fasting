package com.goldforest.capdiet.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.domain.model.PlanType
import java.text.SimpleDateFormat
import java.util.*


class PlanViewModel(private val androidContext : Context) : BaseViewModel() {

    private val _planType = MutableLiveData<PlanType>()
    private val _planTermType = MutableLiveData<Int>()
    private var _startTimeViewString = MutableLiveData<String>()
    private var _fastingTimeViewString = MutableLiveData<String>()
    private var _startDateString = MutableLiveData<String>()
    private var _endDateString = MutableLiveData<String>()

    private var _startTime: MutableLiveData<Long> = MutableLiveData()
    private var _endTime: MutableLiveData<Long> = MutableLiveData()
    private var fastingTimeHour : Int = 16
    private var fastingTimeMin : Int = 0

    val planType: LiveData<PlanType> get() = _planType
    val termType : LiveData<Int> get() = _planTermType

    var startTime: MutableLiveData<Long> = _startTime
    var endTime: MutableLiveData<Long> = _endTime
    val startTimeViewString : MutableLiveData<String> get() = _startTimeViewString
    val fastingTimeViewString : MutableLiveData<String> get() = _fastingTimeViewString

    var startDate: MutableLiveData<String> = MutableLiveData()
    var endDate: MutableLiveData<String> = MutableLiveData()
    val startDateString : MutableLiveData<String> get() = _startDateString
    val endDateString : MutableLiveData<String> get() = _endDateString

    fun setPlanType (planTypeOrdinal: Int) {
        when (planTypeOrdinal) {
            PlanType.PLAN_16_8.ordinal -> _planType.postValue(PlanType.PLAN_16_8)
            else -> _planType.postValue(PlanType.PLAN_5_2)
        }
    }

    fun setStartTimeString(startTime : String) {
        Log.e("HJ", "[HJ] start time : $startTime")
        _startTimeViewString.value = startTime
    }

    fun setStartTime(hourOfDay: Int, minute: Int) {
        val strAmPm: String
        val hour: Int
        val calendar : Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
        calendar.set(Calendar.MINUTE,minute)

        if (hourOfDay < 12) {
            strAmPm = androidContext.getString(R.string.time_am)
            hour = hourOfDay

        } else {
            strAmPm = androidContext.getString(R.string.time_pm)
            hour = hourOfDay - 12
        }
        _startTimeViewString.value = "$hour : $minute $strAmPm"
        _startTime.value = calendar.timeInMillis
        _endTime.value = getEndTime(fastingTimeHour, fastingTimeMin)
    }

    fun setFastingTime(hourOfDay: Int,  minute: Int) {
        fastingTimeHour = hourOfDay
        fastingTimeMin = minute

        _fastingTimeViewString.value = "$hourOfDay ${androidContext.getString(R.string.hours)} $minute ${androidContext.getString(R.string.minutes)}"
        _endTime.value = getEndTime(hourOfDay, minute)
    }

    private fun getEndTime(hourOfDay: Int, minute: Int) : Long {
        val calendar : Calendar = Calendar.getInstance()
        calendar.timeInMillis = startTime.value!!
        calendar.add(Calendar.HOUR_OF_DAY,hourOfDay)
        calendar.add(Calendar.MINUTE,minute)
        Log.e("HJ", "[HJ] End time : ${calendar.get(Calendar.HOUR_OF_DAY)} : ${calendar.get(Calendar.MINUTE)}")
        return calendar.timeInMillis
    }

    fun setPlanTermType(termType  : Int) {
        _planTermType.value = termType
    }

    fun initPlanDate () {
        if (_startDateString.value.isNullOrEmpty()) {
            _startDateString.value = getDateFormatter().format(Calendar.getInstance().time)
        }

        val calendar  = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_YEAR, +1)
        if (_endDateString.value.isNullOrEmpty()) {
            _endDateString.value = getDateFormatter().format(Calendar.getInstance().time)
        }
    }

    private fun getDateFormatter() : SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd", getSystemLocale())
    }

    fun setPlanDate(startDate : String, endDate : String) {

        _startDateString.value = startDate
        _endDateString.value = endDate
    }

    private fun getSystemLocale(): Locale? {
        val packageManager = androidContext.packageManager
        val resources: Resources
        var locale: Locale? = null
        try {
            resources = packageManager.getResourcesForApplication("android")
            locale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                resources.configuration.locales.get(0)
            } else {
                resources.configuration.locale
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

        return locale
    }
}