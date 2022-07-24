package com.lightspeed.lightspeedproject.viewmodel

import android.content.Context
import android.util.Log
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.lightspeed.lightspeedproject.data.Lightspeed
import com.lightspeed.lightspeedproject.data.LightspeedDao
import com.lightspeed.lightspeedproject.data.LightspeedDatabase
import junit.framework.TestCase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FirstViewModelTest : TestCase() {

    val TAG = "TAG"
    private lateinit var db: LightspeedDatabase
    private lateinit var dao: LightspeedDao

    @Before
    public override fun setUp() {
        super.setUp()
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(context, LightspeedDatabase::class.java).build()
        dao = db.lightspeedDao()
    }


    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun writeAndReadLightspeed() = runBlocking {
        val testLightspeed = Lightspeed(
            "1",
            "light speed",
            "https://www.lightspeedhq.com/",
        )

        val listOfTestLightspeed = mutableListOf(testLightspeed)
        dao.insertLightspeed(listOfTestLightspeed)

        val lastInserted = dao.getAllLightspeed()
        assertTrue(testLightspeed.id == lastInserted[0].id)
        assertTrue(testLightspeed.author == lastInserted[0].author)
        assertTrue(testLightspeed.download_url == lastInserted[0].download_url)
    }

}