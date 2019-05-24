package com.goldforest.capdiet.view.calendar

import com.goldforest.capdiet.extentions.getStartEndTimes
import com.goldforest.domain.usercase.GetAllDayResultsByMonth
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class CalendarPresenter(
    private val getAllDayResults: GetAllDayResultsByMonth
) : CalendarContract.Presenter {

    private val TAG = CalendarPresenter::class.java.simpleName

    private var view: CalendarContract.View? = null

    private val job = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + job)

    override fun subscribe(v: CalendarContract.View) {
        view = v
    }

    override fun unsubscribe() {
        view = null
    }

    override fun getDayResults(time: Long) {
        uiScope.launch {
            val (startTime, endTime) = time.getStartEndTimes()
            view?.onDayResultsLoaded(getAllDayResults.get(startTime, endTime))
        }
    }
}
