package com.jesil.spark.home.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable


@Serializable
data class QuoteItemDto(
    @SerializedName("_id") val id : String,
    @SerializedName("author") val author: String,
    @SerializedName("content") val quote: String,
    @SerializedName("tags") val tags: List<String>,
    @SerializedName("authorSlug") val authorSlug: String,
)
