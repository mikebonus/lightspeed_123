package com.lightspeed.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lightspeed.domain.model.Lightspeed

@Database(
    entities = [Lightspeed::class],
    version = 1
)
abstract class LightspeedDatabase : RoomDatabase() {

    abstract fun lightspeedDao(): LightspeedDao

}
