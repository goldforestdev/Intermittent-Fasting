package com.goldforest.capdiet.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.goldforest.capdiet.base.BaseViewModel
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.usercase.GetAllDayResults
import kotlinx.coroutines.*

class CalendarViewModel(
    private val getAllDayResults: GetAllDayResults
) : BaseViewModel() {

    private val TAG = CalendarViewModel::class.java.simpleName

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    val allDayResults: MutableLiveData<List<DayResult>> = MutableLiveData()

    init {
        uiScope.launch {
            allDayResults.value = getAllDayResults.get()
            Log.d(TAG, "[GF] init - getAllDayResults: ${allDayResults.value}")
        }
    }
}