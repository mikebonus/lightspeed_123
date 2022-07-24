package com.lightspeed.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lightspeed.domain.model.Lightspeed

@Dao
interface LightspeedDao {

    // RETRIEVE
    @Query("SELECT * FROM lightspeed")
    fun getAllLightspeed(): List<Lightspeed>

    // INSERT
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertLightspeed(news: List<Lightspeed>)

    // DELETE
    @Query("DELETE FROM lightspeed")
    suspend fun deleteAllLightspeed()

}