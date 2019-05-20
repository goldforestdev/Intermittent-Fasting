package com.goldforest.domain.usecase.impl

import com.goldforest.domain.exceptions.NotExistPlanException
import com.goldforest.domain.model.Plan
import com.goldforest.domain.repository.PlanRepository
import com.goldforest.domain.usercase.HasActivePlan
import com.goldforest.domain.usercase.impl.HasActivePlanUsecase
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class HasActivePlanUsecaseTest {

    @Mock
    private lateinit var planRepository: PlanRepository

    private lateinit var hasActivePlan: HasActivePlan

    private val activePlan = Plan(6L, "active plan", 6, "stime", "etime", 1, "sdate", "edate", false)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        hasActivePlan = HasActivePlanUsecase(planRepository)
    }

    @Test
    fun getTest() = runBlocking {
        `when`(planRepository.getActivePlan()).thenReturn(activePlan)
        assertEquals(activePlan, hasActivePlan.get())
    }

    @Test(expected = NotExistPlanException::class)
    fun `getTest-ActivePlan이 존재하지 않으면 NotExistPlanException을 던져야 한다`() {
        runBlocking {
            `when`(planRepository.getActivePlan()).thenThrow(NotExistPlanException())
            hasActivePlan.get()
        }
    }
}