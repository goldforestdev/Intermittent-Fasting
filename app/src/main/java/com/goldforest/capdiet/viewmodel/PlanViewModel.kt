package com.goldforest.capdiet.viewmodel

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.domain.model.PlanType


class PlanViewModel : BaseViewModel() {

    private val _planType = MutableLiveData<PlanType>()
    private val _timePicker = MutableLiveData<View>()
    private val _startTime = MutableLiveData<Long>()
    private val _endTime = MutableLiveData<Long>()

    val planType: LiveData<PlanType> get() = _planType
    val timePicker: LiveData<View> get() = _timePicker
    var startTime: MutableLiveData<Long> = MutableLiveData()
    var endTime: MutableLiveData<Long> = MutableLiveData()

    fun setPlanType(planTypeOrdinal: Int) {
        when (planTypeOrdinal) {
            PlanType.PLAN_16_8.ordinal -> _planType.postValue(PlanType.PLAN_16_8)
            else -> _planType.postValue(PlanType.PLAN_5_2)
        }
    }

    fun showTimePickerDialog(view: View) {
        _timePicker.postValue(view)
    }

}