package com.jesil.spark.home.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class QuoteDto(
    @SerializedName("count") val count: Int,
    @SerializedName("totalCount") val totalCount: Int,
    @SerializedName("page") val page: Int,
    @SerializedName("totalPages") val totalPages: Int,
    @SerializedName("lastItemIndex") val lastItemIndex: Int,
    @SerializedName("results") val results: List<QuoteItemDto>
)
