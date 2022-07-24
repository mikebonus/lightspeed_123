package com.lightspeed.data.remote

import com.lightspeed.domain.model.Lightspeed

interface FirstApi {

    suspend fun getNormalData(): List<Lightspeed>

}