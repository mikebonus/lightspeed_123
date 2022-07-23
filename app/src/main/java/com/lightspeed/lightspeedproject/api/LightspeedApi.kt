package com.lightspeed.lightspeedproject.api

import com.lightspeed.lightspeedproject.data.Lightspeed

interface LightspeedApi {

    // GET-DATA()
    suspend fun getNormalData(): List<Lightspeed>

    // RANDOM-ITEM()
    // (at BOTTOM)
    suspend fun getLightspeed(): List<Lightspeed>

}