package com.lightspeed.lightspeedproject.api

import com.lightspeed.lightspeedproject.data.Lightspeed

interface FirstApi {

    // GET-DATA()
    suspend fun getNormalData(): List<Lightspeed>

}