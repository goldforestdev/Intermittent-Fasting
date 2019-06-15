package com.goldforest.capdiet.view.dayResult

import com.goldforest.domain.model.DayResult

interface DayResultContract {
    interface View {
        fun onDayResultLoaded(dayResult: DayResult)
        fun test(s: String)

    }

    interface Presenter {
        fun subscribe(view: View)
        fun unsubscribe()
        fun getDayResult(id: Long)
        fun test()
    }
}