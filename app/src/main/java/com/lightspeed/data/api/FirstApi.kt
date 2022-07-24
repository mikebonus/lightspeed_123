package com.lightspeed.data.api

import com.lightspeed.domain.data.Lightspeed

interface FirstApi {

    suspend fun getNormalData(): List<Lightspeed>

}