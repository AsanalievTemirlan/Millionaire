package com.example.millionaire.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.millionaire.data.model.HistoryEntity

@Dao
interface MillionaireDao {

    @Query("SELECT * FROM history")
    fun getRounds(): LiveData<List<HistoryEntity>>

    @Insert
    suspend fun insertRound(round: HistoryEntity)

    @Delete
    suspend fun deleteRound(round: HistoryEntity)


}