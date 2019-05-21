package com.goldforest.data.source.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.goldforest.data.model.DayResultEntity
import com.goldforest.data.model.PlanEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class DayResultDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var dayResultDao: DayResultDao
    private lateinit var planDao: PlanDao

    private val p1 = PlanEntity(1L, "test plan1", 1, "stime", "etime", 1, "sdate", "edate", true)
    private val p2 = PlanEntity(2L, "test plan2", 2, "stime", "etime", 1, "sdate", "edate", true)
    private val planArray = arrayOf(p1, p2)

    private val dr1 = DayResultEntity("1", 1, 1L)
    private val dr2 = DayResultEntity("2", 2, 1L)
    private val dr3 = DayResultEntity("3", 3, 1L)
    private val dr4 = DayResultEntity("4", 1, 2L)
    private val dr5 = DayResultEntity("5", 2, 2L)
    private val dayResultArray = arrayOf(dr1, dr2, dr3, dr4, dr5)

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java).build()
        planDao = db.planDao()
        dayResultDao= db.dayResultDao()

        runBlocking {
            planDao.save(*planArray)
            dayResultDao.save(*dayResultArray)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getTest() = runBlocking {
        Assert.assertEquals(dr1, dayResultDao.get("1"))
        Assert.assertEquals(dr2, dayResultDao.get("2"))
        Assert.assertEquals(dr3, dayResultDao.get("3"))
        Assert.assertEquals(dr4, dayResultDao.get("4"))
        Assert.assertEquals(dr5, dayResultDao.get("5"))
    }

    @Test
    fun getAllByPlanIdTest() = runBlocking {
        var planArrayFromDb = dayResultDao.getAllByPlanId(1L)
        Assert.assertEquals(3, planArrayFromDb.size)

        planArrayFromDb = dayResultDao.getAllByPlanId(2L)
        Assert.assertEquals(2, planArrayFromDb.size)
    }

    @Test
    fun deleteTest() = runBlocking {
        Assert.assertEquals(3, dayResultDao.getAllByPlanId(1L).size)

        dayResultDao.delete(dr1)
        Assert.assertEquals(2, dayResultDao.getAllByPlanId(1L).size)

        dayResultDao.delete(dr2)
        Assert.assertEquals(1, dayResultDao.getAllByPlanId(1L).size)

        dayResultDao.delete(dr3)
        Assert.assertEquals(0, dayResultDao.getAllByPlanId(1L).size)

        Assert.assertEquals(2, dayResultDao.getAllByPlanId(2L).size)

        dayResultDao.delete(dr4)
        Assert.assertEquals(1, dayResultDao.getAllByPlanId(2L).size)

        dayResultDao.delete(dr5)
        Assert.assertEquals(0, dayResultDao.getAllByPlanId(2L).size)
    }

}