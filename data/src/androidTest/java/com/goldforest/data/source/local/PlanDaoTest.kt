package com.goldforest.data.source.local

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.goldforest.data.model.PlanEntity
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException


@RunWith(AndroidJUnit4::class)
class PlanDaoTest {
    private lateinit var db: AppDatabase
    private lateinit var planDao: PlanDao

    private val p1 = PlanEntity(1L, "test plan1", 1, "stime", "etime", 1, "sdate", "edate", true)
    private val p2 = PlanEntity(2L, "test plan2", 2, "stime", "etime", 1, "sdate", "edate", true)
    private val p3 = PlanEntity(3L, "test plan3", 3, "stime", "etime", 1, "sdate", "edate", true)
    private val p4 = PlanEntity(4L, "test plan4", 4, "stime", "etime", 1, "sdate", "edate", true)
    private val p5 = PlanEntity(5L, "test plan5", 5, "stime", "etime", 1, "sdate", "edate", true)
    private val planArray = arrayOf(p1, p2, p3, p4, p5)

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(InstrumentationRegistry.getInstrumentation().context, AppDatabase::class.java).build()
        planDao = db.planDao()

        runBlocking {
            planDao.save(*planArray)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    fun getTest() = runBlocking {
        assertEquals(p1, planDao.get(1L))
        assertEquals(p2, planDao.get(2L))
        assertEquals(p3, planDao.get(3L))
        assertEquals(p4, planDao.get(4L))
        assertEquals(p5, planDao.get(5L))
    }

    @Test
    fun getAllTest() = runBlocking {
        val planArrayFromDb = planDao.getAll()
        assertEquals(5, planArrayFromDb.size)
    }

    @Test
    fun deleteTest() = runBlocking {
        assertEquals(5, planDao.getAll().size)

        planDao.delete(p1)
        assertEquals(4, planDao.getAll().size)

        planDao.delete(p2)
        assertEquals(3, planDao.getAll().size)

        planDao.delete(p3)
        assertEquals(2, planDao.getAll().size)

        planDao.delete(p4)
        assertEquals(1, planDao.getAll().size)

        planDao.delete(p5)
        assertEquals(0, planDao.getAll().size)
    }
}