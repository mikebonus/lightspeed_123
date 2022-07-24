package com.lightspeed.data.api

import com.lightspeed.domain.data.Lightspeed

interface SecondApi {

    suspend fun getLightspeed(): List<Lightspeed>

}