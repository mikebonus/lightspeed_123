package com.lightspeed.lightspeedproject.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Lightspeed::class],
    version = 1
)
abstract class LightspeedDatabase : RoomDatabase() {

    abstract fun lightspeedDao(): LightspeedDao

}
