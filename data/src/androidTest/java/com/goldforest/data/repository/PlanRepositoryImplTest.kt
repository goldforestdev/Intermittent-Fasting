package com.goldforest.data.repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.goldforest.data.source.PlanDataSource
import com.goldforest.data.source.local.AppDatabase
import com.goldforest.domain.exceptions.NotExistPlanException
import com.goldforest.domain.model.Plan
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import kotlin.math.exp


@RunWith(AndroidJUnit4::class)
class PlanRepositoryImplTest {
    private lateinit var db: AppDatabase
    private lateinit var planLocalDataSource: PlanDataSource
    @Mock
    private lateinit var planRemoteDataSource: PlanDataSource
    private lateinit var planRepository: PlanRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java)
            .build()
        planLocalDataSource = db.planDao()
        planRepository = PlanRepositoryImpl(planLocalDataSource, planRemoteDataSource)
    }

    @Test
    fun saveAndGetTest() = runBlocking {
        val plan = Plan(1L, "test plan1", 1, "stime", "etime", 1, "sdate", "edate", true)

        planRepository.save(plan)
        val planFromRepository = planRepository.get(1L)
        assertEquals(plan, planFromRepository)
    }

    @Test
    fun getAllTest() = runBlocking {
        val p1 = Plan(1L, "test plan1", 1, "stime", "etime", 1, "sdate", "edate", true)
        val p2 = Plan(2L, "test plan2", 2, "stime", "etime", 1, "sdate", "edate", true)
        val p3 = Plan(3L, "test plan3", 3, "stime", "etime", 1, "sdate", "edate", true)
        val p4 = Plan(4L, "test plan4", 4, "stime", "etime", 1, "sdate", "edate", true)
        val p5 = Plan(5L, "test plan5", 5, "stime", "etime", 1, "sdate", "edate", true)
        val planArray = arrayOf(p1, p2, p3, p4, p5)

        planRepository.save(*planArray)
        assertEquals(5, planRepository.getAll().size)
    }

    @Test
    fun deleteTest() = runBlocking {
        val p1 = Plan(1L, "test plan1", 1, "stime", "etime", 1, "sdate", "edate", true)
        val p2 = Plan(2L, "test plan2", 2, "stime", "etime", 1, "sdate", "edate", true)
        val p3 = Plan(3L, "test plan3", 3, "stime", "etime", 1, "sdate", "edate", true)
        val p4 = Plan(4L, "test plan4", 4, "stime", "etime", 1, "sdate", "edate", true)
        val p5 = Plan(5L, "test plan5", 5, "stime", "etime", 1, "sdate", "edate", true)
        val planArray = arrayOf(p1, p2, p3, p4, p5)

        planRepository.save(*planArray)
        assertEquals(5, planRepository.getAll().size)

        planRepository.delete(p1)
        assertEquals(4, planRepository.getAll().size)

        planRepository.delete(p2)
        assertEquals(3, planRepository.getAll().size)

        planRepository.delete(p3)
        assertEquals(2, planRepository.getAll().size)

        planRepository.delete(p4)
        assertEquals(1, planRepository.getAll().size)

        planRepository.delete(p5)
        assertEquals(0, planRepository.getAll().size)
    }

    @Test
    fun getActivePlanTest() = runBlocking {
        val activePlan = Plan(6L, "active plan", 6, "stime", "etime", 1, "sdate", "edate", false)

        planRepository.save(activePlan)
        assertEquals(1, planRepository.getAll().size)
        assertEquals(activePlan, planRepository.getActivePlan())
    }

    @Test(expected = NotExistPlanException::class)
    fun `getActivePlanTest-Active_Plan이_존재하지_않으면_NotExistPlanException을_던져야_한다`() = runBlocking {
        val plan = planRepository.getActivePlan()
    }
}