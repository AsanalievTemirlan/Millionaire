package com.example.millionaire.presentation.ui.interfaces

import com.example.millionaire.data.model.HistoryEntity

interface OnClick {
    fun onLongClick(historyEntity: HistoryEntity)
}