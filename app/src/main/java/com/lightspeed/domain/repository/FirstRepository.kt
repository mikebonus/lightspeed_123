package com.lightspeed.domain.repository

import com.lightspeed.data.remote.FirstApi
import com.lightspeed.domain.model.Lightspeed
import com.lightspeed.data.local.LightspeedDatabase
import javax.inject.Inject

class FirstRepository @Inject constructor(
    private val db: LightspeedDatabase,
    private val api: FirstApi,
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