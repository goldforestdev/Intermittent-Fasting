package com.goldforest.capdiet.view.dayResult

import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType

interface DayResultContract {
    interface View {
        fun onDayResultLoaded(dayResult: DayResult)

    }

    interface Presenter {
        fun subscribe(view: View)
        fun unsubscribe()
        fun getDayResult(id: Long)
        fun save(dayResult: DayResult)
        fun update(dayResult: DayResult)
    }
}