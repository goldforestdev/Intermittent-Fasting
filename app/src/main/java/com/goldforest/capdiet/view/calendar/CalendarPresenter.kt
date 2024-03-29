package com.goldforest.capdiet.view.calendar

import android.util.Log
import com.goldforest.capdiet.common.dayOfMonth
import com.goldforest.capdiet.common.get42Days
import com.goldforest.capdiet.common.month
import com.goldforest.capdiet.common.year
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType
import com.goldforest.domain.usercase.GetAllDayResultsByMonth
import com.goldforest.domain.usercase.GetPlans
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

const val INVALID_DATA = -1L

class CalendarPresenter(
    private val getAllDayResults: GetAllDayResultsByMonth,
    private val getPlans: GetPlans,
    private val uiContext: CoroutineContext = Dispatchers.Main,
    private val ioContext: CoroutineContext = Dispatchers.IO
) : CalendarContract.Presenter, CoroutineScope {

    override val coroutineContext: CoroutineContext = Job() + ioContext

    private var view: CalendarContract.View? = null

    override fun subscribe(v: CalendarContract.View) {
        view = v
    }

    override fun unsubscribe() {
        view = null
    }

    override fun getDayResults(time: Long) {
        var dayResultList = listOf<DayResult>()

        launch {
            withContext(ioContext) {
                val dayOfMonthList = time.get42Days()
                val calendar = Calendar.getInstance()
                dayResultList = dayOfMonthList.map {
                    calendar.timeInMillis = it
                    DayResult(
                        it,
                        DayResultType.NOT_INPUT,
                        calendar.year(),
                        calendar.month(),
                        calendar.dayOfMonth(),
                        INVALID_DATA
                    )
                }.toList()

                val plans = getPlans.get()
                getAllDayResults.get(dayOfMonthList.first(), dayOfMonthList.last())
                    .forEach { dFromRepo ->
                        Log.d("TEST", "[GF] getDayResults - $dFromRepo")
                        dayResultList.find {
                            Log.d("TEST2", "[GF] getDayResults - \t$it")
                            it.month == dFromRepo.month && it.dayOfMonth == dFromRepo.dayOfMonth
                        }?.apply {
                            id = dFromRepo.id
                            type = dFromRepo.type
                            planId = dFromRepo.planId
                        }
                    }

                dayResultList.filter { d -> d.planId == INVALID_DATA }
                    .forEach { d ->
                        val plan = plans.find { p ->
                            d.id >= p.startDateTime && d.id <= p.endDateTime
                        }
                        d.planId = plan?.id ?: INVALID_DATA
                    }
            }

            withContext(uiContext) {
                view?.onDayResultsLoaded(dayResultList)
            }
        }
    }

}
