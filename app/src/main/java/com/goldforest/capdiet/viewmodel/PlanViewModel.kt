package com.goldforest.capdiet.viewmodel

import android.content.Context
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.capdiet.data.PlanData
import com.goldforest.domain.model.Plan
import com.goldforest.domain.model.PlanType
import com.goldforest.domain.usercase.CreatePlan
import kotlinx.coroutines.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.coroutines.CoroutineContext


class PlanViewModel(
    private val androidContext : Context,
    private val createPlan: CreatePlan,
    private val uiContext: CoroutineContext = Dispatchers.Main,
    private val ioContext: CoroutineContext = Dispatchers.IO
) : BaseViewModel(), CoroutineScope {
    override val coroutineContext: CoroutineContext = Job() + ioContext

    enum class PlanTermType(val code: Int) {
        PLAN_TERM_4WEEK(0),
        PLAN_TERM_8WEEK(1),
        PLAN_TERM_NO_PERIOD(2),
        PLAN_TERM_USER_SETTING(3);

        companion object {
            fun of(code: Int): PlanTermType = when (code) {
                0 -> PLAN_TERM_4WEEK
                1 -> PLAN_TERM_8WEEK
                2 -> PLAN_TERM_NO_PERIOD
                else -> PLAN_TERM_USER_SETTING
            }
        }
    }

    private val _planType = MutableLiveData<PlanType>()
    private val _planTermType = MutableLiveData<PlanTermType>()
    private var _startTimeViewString = MutableLiveData<String>()
    private var _endTimeViewString = MutableLiveData<String>()
    private var _fastingViewString = MutableLiveData<String>()
    private var _fastingTimeViewString = MutableLiveData<String>()
    private var _startDateString = MutableLiveData<String>()
    private var _endDateString = MutableLiveData<String>()

    private var _startTime: MutableLiveData<Long> = MutableLiveData()
    private var _endTime: MutableLiveData<Long> = MutableLiveData()
    private var fastingTimeHour : Int = 16
    private var fastingTimeMin : Int = 0

    val planType: LiveData<PlanType> get() = _planType
    val termType : LiveData<PlanTermType> get() = _planTermType

    val startTime: MutableLiveData<Long> get() = _startTime
    val endTime: MutableLiveData<Long> get() = _endTime
    val startTimeViewString : MutableLiveData<String> get() = _startTimeViewString
    val endTimeViewString : MutableLiveData<String> get() = _endTimeViewString
    val fastingTimeViewString : MutableLiveData<String> get() = _fastingTimeViewString
    val fastingViewString get() = _fastingViewString

    var startDate: MutableLiveData<String> = MutableLiveData()
    var endDate: MutableLiveData<String> = MutableLiveData()
    val startDateString : MutableLiveData<String> get() = _startDateString
    val endDateString : MutableLiveData<String> get() = _endDateString
    private var startDateTime : Long = 0L
    private var endDateTime : Long = 0L

    private var planData : PlanData? = null

    fun setPlanType (planTypeOrdinal: Int) {
        when (planTypeOrdinal) {
            PlanType.PLAN_16_8.ordinal -> _planType.postValue(PlanType.PLAN_16_8)
            else -> _planType.postValue(PlanType.PLAN_5_2)
        }
    }

    fun setStartTime(hourOfDay: Int, minute: Int) {
        val calendar : Calendar = Calendar.getInstance()
        calendar.set(Calendar.HOUR_OF_DAY,hourOfDay)
        calendar.set(Calendar.MINUTE,minute)

        val (strAmPm: String, hour: Int) = getHourOfDay(hourOfDay)
        _startTimeViewString.value = "$hour : $minute $strAmPm"
        _startTime.value = calendar.timeInMillis
        Log.e("HJ","[HJ]Start time : ${_startTime.value}")
        _endTime.value = getEndTime(fastingTimeHour, fastingTimeMin)

        setFastingString()
    }

    private fun getEndTime(hourOfDay: Int, minute: Int) : Long {
        val calendar : Calendar = Calendar.getInstance()
        calendar.timeInMillis = startTime.value!!
        calendar.add(Calendar.HOUR_OF_DAY,hourOfDay)
        calendar.add(Calendar.MINUTE,minute)

        val (strAmPm: String, hour: Int) = getHourOfDay(calendar.get(Calendar.HOUR_OF_DAY))
        _endTimeViewString.value = "$hour : ${calendar.get(Calendar.MINUTE)} $strAmPm"

        setFastingString()
        return calendar.timeInMillis
    }

    private fun getHourOfDay(hourOfDay: Int): Pair<String, Int> {
        val strAmPm: String
        val hour: Int
        if (hourOfDay < 12) {
            strAmPm = androidContext.getString(R.string.time_am)
            hour = hourOfDay

        } else {
            strAmPm = androidContext.getString(R.string.time_pm)
            hour = hourOfDay - 12
        }
        return Pair(strAmPm, hour)
    }

    fun setFastingTime(hourOfDay: Int,  minute: Int) {
        fastingTimeHour = hourOfDay
        fastingTimeMin = minute

        _fastingTimeViewString.value = "$hourOfDay ${androidContext.getString(R.string.hours)} $minute ${androidContext.getString(R.string.minutes)}"
        _endTime.value = getEndTime(hourOfDay, minute)
    }

    fun setPlanTermType(termType  : Int) {
        _planTermType.value = PlanTermType.of(termType)
        setPeriodEndDate()
    }

    fun initPlanDate () {
        _planTermType.value = PlanTermType.PLAN_TERM_4WEEK

        if (_startDateString.value.isNullOrEmpty()) {
            _startDateString.value = getDateFormatter().format(Calendar.getInstance().time)
        }

        val calendar  = Calendar.getInstance()
        calendar.add(Calendar.WEEK_OF_YEAR, +4)
        if (_endDateString.value.isNullOrEmpty()) {
            _endDateString.value = getDateFormatter().format(calendar.time)
        }
    }

    private fun getDateFormatter() : SimpleDateFormat {
        return SimpleDateFormat("yyyy-MM-dd", getSystemLocale())
    }

    fun setStartDate(year: Int, month : Int, day : Int) {
        val calendar = getDateCalendar(year, month, day)
        _startDateString.value = getDateFormatter().format(calendar.time)

        setPeriodEndDate()
    }

    fun setEndDate(year: Int, month : Int, day : Int) {
        val calendar = getDateCalendar(year, month, day)
        _endDateString.value = getDateFormatter().format(calendar.time)
    }

    private fun setPeriodEndDate() {
        val calendar = getDateCalendar(_startDateString.value)
        if (_planTermType.value == PlanTermType.PLAN_TERM_4WEEK) {
            calendar.add(Calendar.WEEK_OF_YEAR, 4)
            _endDateString.value = getDateFormatter().format(calendar.time)
        }
        else if (_planTermType.value == PlanTermType.PLAN_TERM_8WEEK) {
            calendar.add(Calendar.WEEK_OF_YEAR, 8)
            _endDateString.value = getDateFormatter().format(calendar.time)
        }
    }

    fun getDateCalendar(dateString: String?) : Calendar {
        val calendar = Calendar.getInstance()
        val sdf : SimpleDateFormat = getDateFormatter()
        calendar.time = sdf.parse(dateString)
        return calendar
    }

    fun getPlanStartCalendar() : Calendar {
        val cal = Calendar.getInstance()
        cal.timeInMillis = _startTime.value!!

        val startCalendar = getDateCalendar(_startDateString.value)
        startCalendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY))
        startCalendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE))

        return startCalendar
    }

    fun getPlanEndCalendar() : Calendar {
        val cal = Calendar.getInstance()
        cal.timeInMillis = _endTime.value!!
        val endCalendar = getDateCalendar(_endDateString.value)
        endCalendar.set(Calendar.HOUR_OF_DAY, cal.get(Calendar.HOUR_OF_DAY))
        endCalendar.set(Calendar.MINUTE, cal.get(Calendar.MINUTE))

        return endCalendar
    }

    private fun getDateCalendar(year: Int, month: Int, day: Int): Calendar {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.YEAR, year)
        calendar.set(Calendar.MONTH, month)
        calendar.set(Calendar.DAY_OF_MONTH, day)
        return calendar
    }

    fun setPlanDate(startDate : String, endDate : String) {

        _startDateString.value = startDate
        _endDateString.value = endDate
    }

    private fun setFastingString() {
        _fastingViewString.value = "${_startTimeViewString.value} ~ ${_endTimeViewString.value}"
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

    fun createPlan() {
        launch {
            withContext(ioContext) {
                val plan : Plan = Plan(startDateTime,"Test",PlanType.PLAN_16_8,_startTimeViewString.value!!,_endTimeViewString.value!!,
                    0, _startDateString.value!!, _endDateString.value!!, startDateTime, endDateTime, false )
                createPlan.save(plan)
            }
        }
    }
}