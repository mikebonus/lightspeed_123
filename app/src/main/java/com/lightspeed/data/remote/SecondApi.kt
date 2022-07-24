package com.lightspeed.data.remote

import com.lightspeed.domain.model.Lightspeed

interface SecondApi {

    suspend fun getLightspeed(): List<Lightspeed>

}