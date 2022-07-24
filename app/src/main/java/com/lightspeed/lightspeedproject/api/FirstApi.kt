package com.lightspeed.lightspeedproject.api

import com.lightspeed.lightspeedproject.data.Lightspeed

interface FirstApi {

    suspend fun getNormalData(): List<Lightspeed>

}