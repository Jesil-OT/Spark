package com.jesil.spark.home.data.local.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "quotes")
data class QuoteEntity(
    @PrimaryKey(autoGenerate = false) val id: String,
    val author: String,
    val quote: String,
    val tags: List<String>,
    val authorSlug: String,
)
