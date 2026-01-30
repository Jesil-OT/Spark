package com.jesil.spark.home.data.remote.model

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName

data class QuoteDto(
    @SerializedName("quotes") val results: List<QuoteItemDto>,
    @SerializedName("total") val count: Int,
    @SerializedName("skip") val skip: Int,
    @SerializedName("limit") val limit: Int,
)
