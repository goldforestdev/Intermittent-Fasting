package com.goldforest.capdiet.viewmodel

import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.capdiet.viewmodel.PlanType.*

enum class PlanType {
    PLAN_16_8,
    PLAN_5_2
}

class PlanViewModel : BaseViewModel() {
    var planType : MutableLiveData<PlanType> = MutableLiveData<PlanType>().apply { postValue(PLAN_16_8) }

    fun setPlanType (planTypeOrdinal :Int) {
        planType.value = when (planTypeOrdinal) {
            PLAN_16_8.ordinal ->  PLAN_16_8
            else -> PLAN_5_2
        }
    }
}