package com.jesil.spark.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.jesil.spark.home.data.local.model.DailyQuoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DailyQuoteDao {

    @Query("SELECT * FROM daily_quotes")
    fun getDailyQuote(): Flow<DailyQuoteEntity?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyQuote(quote: DailyQuoteEntity)

    @Query("DELETE FROM daily_quotes")
    suspend fun deleteDailyQuote()
}