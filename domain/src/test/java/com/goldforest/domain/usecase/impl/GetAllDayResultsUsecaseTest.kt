package com.goldforest.domain.usecase.impl

import com.goldforest.domain.exceptions.NotExistDayResultException
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType
import com.goldforest.domain.repository.DayResultRepository
import com.goldforest.domain.usercase.GetAllDayResultsByMonth
import com.goldforest.domain.usercase.impl.GetAllDayResultsByMonthUsecase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations
import java.util.*

class GetAllDayResultsUsecaseTest {

    @Mock
    private lateinit var dayResultRepository: DayResultRepository

    private lateinit var getAllDayResults: GetAllDayResultsByMonth

    private val startTime = 10L
    private val endTime = 20L

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getAllDayResults = GetAllDayResultsByMonthUsecase(dayResultRepository)
    }

    @Test
    fun getTest() = runBlocking {
        val dayResults = listOf(
            DayResult(1, DayResultType.FAILED, Calendar.JUNE, 5, 1L),
            DayResult(2, DayResultType.SUCCESS, Calendar.JUNE, 6, 1L),
            DayResult(3, DayResultType.SUCCESS, Calendar.JUNE, 7, 1L),
            DayResult(4, DayResultType.FAILED, Calendar.JUNE, 8, 2L),
            DayResult(5, DayResultType.SUCCESS, Calendar.JUNE, 9, 2L)
        )

        `when`(dayResultRepository.getAllByMonth(startTime, endTime)).thenReturn(dayResults)
        val allDayResults = getAllDayResults.get(startTime, endTime)
        assertEquals(5, allDayResults.size)
    }

    @Test(expected = NotExistDayResultException::class)
    fun `getTest - DayResult가 존재하지 않으면 NotExistDayResultException을 던져야 한다`() {
        runBlocking {
            `when`(dayResultRepository.getAllByMonth(startTime, endTime)).thenThrow(NotExistDayResultException())
            getAllDayResults.get(startTime, endTime)
        }
    }
}