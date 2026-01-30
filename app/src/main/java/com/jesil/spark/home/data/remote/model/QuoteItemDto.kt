package com.jesil.spark.home.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class QuoteItemDto(
    @SerializedName("id") val id : String,
    @SerializedName("author") val author: String,
    @SerializedName("quote") val quote: String,
)
