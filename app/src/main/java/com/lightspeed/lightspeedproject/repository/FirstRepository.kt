package com.lightspeed.lightspeedproject.repository

import com.lightspeed.lightspeedproject.api.LightspeedApi
import com.lightspeed.lightspeedproject.data.Lightspeed
import com.lightspeed.lightspeedproject.data.LightspeedDatabase
import javax.inject.Inject

class FirstRepository @Inject constructor(
    private val db: LightspeedDatabase,
    private val api: LightspeedApi,
) {

    private val lightspeedDao = db.lightspeedDao()

    suspend fun getLightspeedFromAPI(): List<Lightspeed> {
        if (api.getNormalData() != null) {
            lightspeedDao.deleteAllLightspeed()
            lightspeedDao.insertLightspeed(api.getNormalData())
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