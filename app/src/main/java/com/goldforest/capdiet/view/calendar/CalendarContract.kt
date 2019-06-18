package com.goldforest.capdiet.view.calendar

import com.goldforest.domain.model.DayResult

interface CalendarContract {
    interface View {
        fun onDayResultsLoaded(dayResults: List<DayResult>)
        fun onPlanIdFound(dayResult: DayResult)

    }

    interface Presenter {
        fun subscribe(view: View)
        fun unsubscribe()
        fun getDayResults(time: Long)
        fun findPlanId(dayResult: DayResult)
    }
}