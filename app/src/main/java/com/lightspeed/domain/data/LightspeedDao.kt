package com.lightspeed.domain.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

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