package com.goldforest.capdiet.viewmodel

import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.domain.model.PlanType


class PlanViewModel : BaseViewModel() {

    private val _planType = MutableLiveData<PlanType>()
    private val _setTimeValue = MutableLiveData<View>()
    private val _startTime = MutableLiveData<Long>()
    private val _endTime = MutableLiveData<Long>()

    private var _startTimeViewString = MutableLiveData<String>()
    private var _fastingTimeViewString = MutableLiveData<String>()

    private var _nextFragmentShowValue = MutableLiveData<Boolean>()

    val planType: LiveData<PlanType> get() = _planType
    val setTimeValue: LiveData<View> get() = _setTimeValue

    var startTime: MutableLiveData<Long> = MutableLiveData()
    var endTime: MutableLiveData<Long> = MutableLiveData()

    val startTimeViewString : MutableLiveData<String> get() = _startTimeViewString
    val fastingTimeViewString : MutableLiveData<String> get() = _fastingTimeViewString

    val nextFragmentShow : LiveData<Boolean> get() = _nextFragmentShowValue

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

    fun setStartTimeString(startTime : String) {
        Log.e("HJ", "[HJ] start time : $startTime")
        _startTimeViewString.value = startTime
    }

    fun setFastingTimeString(fastingTime : String) {
        _fastingTimeViewString.value = fastingTime
    }

    fun nextButtonClick () {
        _nextFragmentShowValue.value = true
    }
}