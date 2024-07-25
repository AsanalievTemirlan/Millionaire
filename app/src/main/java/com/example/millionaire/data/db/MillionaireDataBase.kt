package com.example.millionaire.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.millionaire.data.dao.MillionaireDao
import com.example.millionaire.data.model.HistoryEntity

@Database(entities = [HistoryEntity::class], version = 1)
abstract class MillionaireDataBase: RoomDatabase() {
    abstract fun millionaireDao(): MillionaireDao
}