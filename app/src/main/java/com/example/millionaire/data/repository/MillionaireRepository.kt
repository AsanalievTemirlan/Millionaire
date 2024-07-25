package com.example.millionaire.data.repository

import com.example.millionaire.data.dao.MillionaireDao
import com.example.millionaire.data.model.HistoryEntity
import javax.inject.Inject

class MillionaireRepository @Inject constructor(private val dao: MillionaireDao) {

    suspend fun insertRound(historyEntity: HistoryEntity) = dao.insertRound(historyEntity)

    suspend fun deleteRound(historyEntity: HistoryEntity) = dao.deleteRound(historyEntity)

    fun getRounds() = dao.getRounds()
}