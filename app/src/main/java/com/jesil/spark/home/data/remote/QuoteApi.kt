package com.jesil.spark.home.data.remote

import com.jesil.spark.home.data.remote.model.QuoteDto
import com.jesil.spark.home.data.remote.model.QuoteItemDto
import retrofit2.http.GET
import retrofit2.http.Query


interface QuoteApi {

    @GET("/quotes")
    suspend fun getQuotes(
        @Query("page") page: Int
    ): QuoteDto

    @GET("/random")
    suspend fun getRandomQuote(): QuoteItemDto

}