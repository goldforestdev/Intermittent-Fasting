package com.goldforest.capdiet.view.calendar

import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType
import com.goldforest.domain.usercase.GetAllDayResultsByMonth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.*

class CalendarPresenterTest {

    @Mock
    private lateinit var view: CalendarContract.View

    @Mock
    private lateinit var getAllDayResults: GetAllDayResultsByMonth

    private lateinit var presenter: CalendarContract.Presenter

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        presenter = CalendarPresenter(getAllDayResults, Dispatchers.Unconfined, Dispatchers.Unconfined)
        presenter.subscribe(view)
    }

    @Test
    fun getDayResultsTest() = runBlocking {
        val calendar = Calendar.getInstance()
        calendar.set(2019, Calendar.JUNE, 4) // 2019-06-04
        val time = calendar.timeInMillis
        val dayOfMonthList = time.get42Days()

        val mockDayResultList = listOf(
            getDayResult(2019, Calendar.JUNE, 1, DayResultType.SUCCESS),
            getDayResult(2019, Calendar.JUNE, 2, DayResultType.FAILED),
            getDayResult(2019, Calendar.JUNE, 3, DayResultType.SUCCESS)
        )

        val expectedResult = listOf(
            DayResult(id=-1, type= DayResultType.NOT_INPUT, month=5, dayOfMonth=26, planId=-1),
            DayResult(id=-1, type= DayResultType.NOT_INPUT, month=5, dayOfMonth=27, planId=-1),
            DayResult(id=-1, type= DayResultType.NOT_INPUT, month=5, dayOfMonth=28, planId=-1),
            DayResult(id=-1, type= DayResultType.NOT_INPUT, month=5, dayOfMonth=29, planId=-1),
            DayResult(id=-1, type= DayResultType.NOT_INPUT, month=5, dayOfMonth=30, planId=-1),
            DayResult(id=-1, type= DayResultType.NOT_INPUT, month=5, dayOfMonth=31, planId=-1),
            getDayResult(2019, Calendar.JUNE, 1, DayResultType.SUCCESS),
            getDayResult(2019, Calendar.JUNE, 2, DayResultType.FAILED),
            getDayResult(2019, Calendar.JUNE, 3, DayResultType.SUCCESS),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=4, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=5, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=6, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=7, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=8, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=9, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=10, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=11, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=12, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=13, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=14, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=15, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=16, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=17, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=18, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=19, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=20, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=21, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=22, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=23, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=24, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=25, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=26, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=27, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=28, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=29, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=6, dayOfMonth=30, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=7, dayOfMonth=1, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=7, dayOfMonth=2, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=7, dayOfMonth=3, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=7, dayOfMonth=4, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=7, dayOfMonth=5, planId=-1),
            DayResult(id=-1, type=DayResultType.NOT_INPUT, month=7, dayOfMonth=6, planId=-1)
        )


        `when`(getAllDayResults.get(dayOfMonthList.first(), dayOfMonthList.last())).thenReturn(mockDayResultList)

        presenter.getDayResults(time)

        verify(view).onDayResultsLoaded(expectedResult)
    }

    private fun getDayResult(year: Int, month: Int, day: Int, type: DayResultType): DayResult {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day, 0, 0, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        return DayResult(calendar.timeInMillis, type, month + 1, day, 0L)
    }
}

private fun Long.get42Days(): List<Long> {
    val months = ArrayList<Long>()
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    calendar.set(Calendar.DATE, 1)
    calendar.set(Calendar.HOUR_OF_DAY, 6)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    when (calendar.get(Calendar.DAY_OF_WEEK)) {
        Calendar.MONDAY -> calendar.add(Calendar.DATE, -1)
        Calendar.TUESDAY -> calendar.add(Calendar.DATE, -2)
        Calendar.WEDNESDAY -> calendar.add(Calendar.DATE, -3)
        Calendar.THURSDAY -> calendar.add(Calendar.DATE, -4)
        Calendar.FRIDAY -> calendar.add(Calendar.DATE, -5)
        Calendar.SATURDAY -> calendar.add(Calendar.DATE, -6)
        else -> {
        }
    }

    for (i in 0..41) {
        months.add(calendar.timeInMillis)
        calendar.add(Calendar.DATE, 1)
    }

    return months
}