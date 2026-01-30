package com.jesil.spark.core.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jesil.spark.home.data.local.Converters
import com.jesil.spark.home.data.local.DailyQuoteDao
import com.jesil.spark.home.data.local.QuoteDao
import com.jesil.spark.home.data.local.model.DailyQuoteEntity
import com.jesil.spark.home.data.local.model.QuoteEntity

@Database(
    entities = [
        QuoteEntity::class,
        DailyQuoteEntity::class
               ],
    version = 2,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun quoteDao(): QuoteDao

    abstract fun dailyQuoteDao(): DailyQuoteDao

    companion object {
        const val DATABASE_NAME = "spark_db"
    }
}