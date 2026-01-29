package com.jesil.spark.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesil.spark.home.data.local.model.DailyQuoteEntity
import com.jesil.spark.home.data.local.model.QuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuoteDao{

    @Query("SELECT * FROM quotes")
    fun getQuotes(): Flow<List<QuoteEntity>>

    @Query("SELECT * FROM quotes WHERE id = :id")
    fun getQuoteById(id: String): Flow<QuoteEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertQuotes(quote: List<QuoteEntity>)

    @Query("DELETE FROM quotes")
    suspend fun deleteQuotes()
}