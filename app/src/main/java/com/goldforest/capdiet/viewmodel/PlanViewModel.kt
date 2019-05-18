package com.goldforest.capdiet.viewmodel

import android.view.View
import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.R
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.capdiet.viewmodel.PlanType.*

enum class PlanType {
    PLAN_16_8,
    PLAN_5_2
}

class PlanViewModel : BaseViewModel() {
    var planType : MutableLiveData<PlanType> = MutableLiveData<PlanType>().apply { postValue(PLAN_16_8) }
    var isChecked : Int = 0

    val planClickListener = View.OnClickListener {view ->
        when(view.id) {
            R.id.rbPlan1 -> {
                planType.value = PLAN_16_8
                isChecked = 0
            } else -> {
                planType.value = PLAN_5_2
                isChecked = 1
            }
        }
    }

}