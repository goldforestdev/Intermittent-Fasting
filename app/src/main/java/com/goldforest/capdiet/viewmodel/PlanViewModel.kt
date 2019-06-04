package com.goldforest.capdiet.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.domain.model.PlanType
import java.text.SimpleDateFormat
import java.util.*


class PlanViewModel(private val androidContext : Context) : BaseViewModel() {

    private val _planType = MutableLiveData<PlanType>()
    private val _planTermType = MutableLiveData<Int>()
    private val _setTimeValue = MutableLiveData<View>()
    private val _setDateValue = MutableLiveData<View>()
    private var _startTimeViewString = MutableLiveData<String>()
    private var _fastingTimeViewString = MutableLiveData<String>()
    private var _startDateString = MutableLiveData<String>()
    private var _endDateString = MutableLiveData<String>()

    val planType: LiveData<PlanType> get() = _planType
    val termType : LiveData<Int> get() = _planTermType
    val setTimeValue: LiveData<View> get() = _setTimeValue
    val setDateValue: LiveData<View> get() = _setDateValue

    var startTime: MutableLiveData<Long> = MutableLiveData()
    var endTime: MutableLiveData<Long> = MutableLiveData()

    var startDate: MutableLiveData<String> = MutableLiveData()
    var endDate: MutableLiveData<String> = MutableLiveData()

    val startTimeViewString : MutableLiveData<String> get() = _startTimeViewString
    val fastingTimeViewString : MutableLiveData<String> get() = _fastingTimeViewString

    val startDateString : MutableLiveData<String> get() = _startDateString
    val endDateString : MutableLiveData<String> get() = _endDateString

    fun setPlanType (planTypeOrdinal: Int) {
        when (planTypeOrdinal) {
            PlanType.PLAN_16_8.ordinal -> _planType.postValue(PlanType.PLAN_16_8)
            else -> _planType.postValue(PlanType.PLAN_5_2)
        }
    }

    fun showTimePickerDialog (view: View) {
        _setTimeValue.postValue(view)
    }

    fun initTimPickerValue () {
        _setTimeValue.postValue(null)
    }

    fun showDateViewDialog (view: View) {
        _setDateValue.postValue(view)
    }

    fun initDateViewValue () {
        _setTimeValue.postValue(null)
    }

    fun setStartTimeString(startTime : String) {
        Log.e("HJ", "[HJ] start time : $startTime")
        _startTimeViewString.value = startTime
    }

    fun setFastingTimeString(fastingTime : String) {
        _fastingTimeViewString.value = fastingTime
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