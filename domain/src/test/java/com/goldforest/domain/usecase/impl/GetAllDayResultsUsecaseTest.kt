package com.goldforest.domain.usecase.impl

import com.goldforest.domain.exceptions.NotExistDayResultException
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.repository.DayResultRepository
import com.goldforest.domain.usercase.GetAllDayResults
import com.goldforest.domain.usercase.impl.GetAllDayResultsUsecase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class GetAllDayResultsUsecaseTest {

    @Mock
    private lateinit var dayResultRepository: DayResultRepository

    private lateinit var getAllDayResults: GetAllDayResults

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        getAllDayResults = GetAllDayResultsUsecase(dayResultRepository)
    }

    @Test
    fun getTest() = runBlocking {
        val dayResults = listOf(
            DayResult("1", 0, 1L),
            DayResult("2", 1, 1L),
            DayResult("3", 1, 1L),
            DayResult("1", 0, 2L),
            DayResult("2", 2, 2L)
        )

        `when`(dayResultRepository.getAll()).thenReturn(dayResults)
        val allDayResults = getAllDayResults.get()
        assertEquals(5, allDayResults.size)
    }

    @Test(expected = NotExistDayResultException::class)
    fun `getTest - DayResult가 존재하지 않으면 NotExistDayResultException을 던져야 한다`() {
        runBlocking {
            `when`(dayResultRepository.getAll()).thenThrow(NotExistDayResultException())
            getAllDayResults.get()
        }
    }
}