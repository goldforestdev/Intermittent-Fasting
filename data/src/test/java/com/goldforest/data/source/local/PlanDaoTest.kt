package com.goldforest.data.source.local

import android.content.Context
import androidx.room.Room
import com.goldforest.data.model.PlanEntity
import org.hamcrest.core.IsEqual.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test
import java.io.IOException

class PlanDaoTest {
    private lateinit var planDao: PlanDao
    private lateinit var db: AppDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, AppDatabase::class.java).build()
        planDao = db.planDao()
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun writeUserAndReadInList() {
        val plan: PlanEntity = PlanEntity(1L, "test plan", 1, "stime", "etime", 1, "sdate", "edate", true)
//        planDao.save(plan)
        val byName = planDao.findUsersByName("george")
        assertThat(byName.get(0), equalTo(plan))
    }
}