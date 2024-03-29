package com.goldforest.data.repository

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.goldforest.data.model.PlanEntity
import com.goldforest.data.source.DayResultDataSource
import com.goldforest.data.source.PlanDataSource
import com.goldforest.data.source.local.AppDatabase
import com.goldforest.domain.model.DayResult
import com.goldforest.domain.model.DayResultType
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.util.*


@RunWith(AndroidJUnit4::class)
class DayResultRepositoryImplTest {
    private lateinit var db: AppDatabase
    private lateinit var planDao: PlanDataSource
    private lateinit var dayResultLocalDataSource: DayResultDataSource
    @Mock
    private lateinit var dayResultRemoteDataSource: DayResultDataSource
    private lateinit var dayResultRepository: DayResultRepositoryImpl

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)

        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java)
            .build()
        planDao = db.planDao()
        dayResultLocalDataSource = db.dayResultDao()
        dayResultRepository = DayResultRepositoryImpl(dayResultLocalDataSource, dayResultRemoteDataSource)

        val p1 = PlanEntity(1L, "test plan1", 1, "stime", "etime", 1, "sdate", "edate", true)
        val p2 = PlanEntity(2L, "test plan2", 2, "stime", "etime", 1, "sdate", "edate", true)
        val planArray = arrayOf(p1, p2)
        val dr1 = DayResult(1, DayResultType.FAILED, Calendar.JUNE, 5, 1L)
        val dr2 = DayResult(2, DayResultType.SUCCESS, Calendar.JUNE, 6, 1L)
        val dr3 = DayResult(3, DayResultType.SUCCESS, Calendar.JUNE, 7, 1L)
        val dr4 = DayResult(4, DayResultType.FAILED, Calendar.JUNE, 8, 2L)
        val dr5 = DayResult(5, DayResultType.SUCCESS, Calendar.JUNE, 9, 2L)

        val dayResultArray = arrayOf(dr1, dr2, dr3, dr4, dr5)

        runBlocking {
            planDao.save(*planArray)
            dayResultRepository.save(*dayResultArray)
        }
    }

    @Test
    fun saveAndGetTest() = runBlocking {
        val dr1 = DayResult(1, DayResultType.SUCCESS, Calendar.JUNE, 5, 1L)
        val dayResultFromRepository = dayResultRepository.get(1)
        assertEquals(dr1, dayResultFromRepository)
    }

    @Test
    fun getAllByPlanIdTest() = runBlocking {
        assertEquals(3, dayResultRepository.getAllByPlanId(1L).size)
        assertEquals(2, dayResultRepository.getAllByPlanId(2L).size)
    }

    @Test
    fun deleteTest() = runBlocking {
        val dr1 = DayResult(1, DayResultType.FAILED, Calendar.JUNE, 5, 1L)
        val dr2 = DayResult(2, DayResultType.SUCCESS, Calendar.JUNE, 6, 1L)
        val dr3 = DayResult(3, DayResultType.SUCCESS, Calendar.JUNE, 7, 1L)
        val dr4 = DayResult(4, DayResultType.FAILED, Calendar.JUNE, 8, 2L)
        val dr5 = DayResult(5, DayResultType.SUCCESS, Calendar.JUNE, 9, 2L)

        assertEquals(3, dayResultRepository.getAllByPlanId(1L).size)

        dayResultRepository.delete(dr1)
        assertEquals(2, dayResultRepository.getAllByPlanId(1L).size)

        dayResultRepository.delete(dr2)
        assertEquals(1, dayResultRepository.getAllByPlanId(1L).size)

        dayResultRepository.delete(dr3)
        assertEquals(0, dayResultRepository.getAllByPlanId(1L).size)

        assertEquals(2, dayResultRepository.getAllByPlanId(2L).size)

        dayResultRepository.delete(dr4)
        assertEquals(1, dayResultRepository.getAllByPlanId(2L).size)

        dayResultRepository.delete(dr5)
        assertEquals(0, dayResultRepository.getAllByPlanId(2L).size)
    }

    @Test
    fun `getAllTest-without_planId`() = runBlocking {
        val startTime = 10L
        val endTime = 20L
        assertEquals(5, dayResultRepository.getAllByMonth(startTime, endTime).size)
    }
}