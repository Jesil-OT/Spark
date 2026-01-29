package com.jesil.spark.home.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "daily_quotes")
data class DailyQuoteEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val author: String,
    val quote: String,
    val tags: List<String>,
    val authorSlug: String,
    val date: Long
)
