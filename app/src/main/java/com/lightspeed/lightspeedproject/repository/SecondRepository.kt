package com.lightspeed.lightspeedproject.repository

import com.lightspeed.lightspeedproject.api.LightspeedApi
import com.lightspeed.lightspeedproject.data.Lightspeed
import com.lightspeed.lightspeedproject.data.LightspeedDatabase
import javax.inject.Inject

class SecondRepository @Inject constructor(
    private val db: LightspeedDatabase,
    private val api: LightspeedApi,
) {

    private val lightspeedDao = db.lightspeedDao()

    suspend fun getLightspeedFromAPI(): List<Lightspeed> {
        if (api.getLightspeed() != null) {
            lightspeedDao.deleteAllLightspeed()
            lightspeedDao.insertLightspeed(api.getLightspeed())
        }

        return lightspeedDao.getAllLightspeed()
    }

    fun getLightspeedFromDB(): List<Lightspeed> {
        if (lightspeedDao.getAllLightspeed() != null) {
            return lightspeedDao.getAllLightspeed()
        }
        return lightspeedDao.getAllLightspeed()
    }

}