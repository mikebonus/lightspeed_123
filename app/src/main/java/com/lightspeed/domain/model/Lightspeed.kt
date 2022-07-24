package com.lightspeed.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "lightspeed")
data class Lightspeed(

    @PrimaryKey
    @Expose
    @SerializedName("id")
    val id: String,

    @Expose
    @SerializedName("author")
    val author: String,

    @Expose
    @SerializedName("download_url")
    val download_url: String,

)