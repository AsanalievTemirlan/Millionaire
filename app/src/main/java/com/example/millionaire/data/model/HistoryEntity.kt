package com.example.millionaire.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "history")
data class HistoryEntity(
    @PrimaryKey (autoGenerate = true)
    val id: Int = 0,
    val won: String,
    val sum: Int,
    val level: Int,
)
