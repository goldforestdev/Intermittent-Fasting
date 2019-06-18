package com.goldforest.capdiet.view.calendar

import com.goldforest.capdiet.common.dayOfMonth
import com.goldforest.capdiet.common.get42Days
import com.goldforest.capdiet.common.month
import com.goldforest.capdiet.common.year
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType
import com.goldforest.domain.usercase.GetAllDayResultsByMonth
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

const val INVALID_DATA = -1L

class CalendarPresenter(
    private val getAllDayResults: GetAllDayResultsByMonth,
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
        val dayOfMonthList = time.get42Days()
        val calendar = Calendar.getInstance()
        val dayResultList = dayOfMonthList.map {
            calendar.timeInMillis = it
            DayResult(INVALID_DATA, DayResultType.NOT_INPUT, calendar.year(), calendar.month(), calendar.dayOfMonth(), INVALID_DATA)
        }.toList()

        launch {
            withContext(ioContext) {
                getAllDayResults.get(dayOfMonthList.first(), dayOfMonthList.last())
            }.forEach { dFromRepo ->
                dayResultList.find {
                    it.month == dFromRepo.month && it.dayOfMonth == dFromRepo.dayOfMonth
                }?.apply {
                    id = dFromRepo.id
                    type = dFromRepo.type
                    planId = dFromRepo.planId
                }
            }

            dayResultList[7].apply {
                id = System.currentTimeMillis()
                type = DayResultType.NOT_INPUT
            }
            dayResultList[8].apply {
                id = System.currentTimeMillis()
                type = DayResultType.SUCCESS
            }
            dayResultList[9].apply {
                id = System.currentTimeMillis()
                type = DayResultType.FAILED
            }

            withContext(uiContext) {
                view?.onDayResultsLoaded(dayResultList)
            }
        }
    }

    override fun findPlanId(dayResult: DayResult) {
        launch {
            withContext(ioContext) {
                TODO("Get all plans to find the plan id")
            }
        }

        view?.onPlanIdFound(dayResult)
    }
}
