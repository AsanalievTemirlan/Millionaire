package com.example.millionaire.presentation.ui.fragment.history

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.millionaire.data.model.HistoryEntity
import com.example.millionaire.data.repository.MillionaireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HistoryViewModel @Inject constructor(private val repository: MillionaireRepository) :
    ViewModel() {

    fun getHistory() = repository.getRounds()

    fun delete(historyEntity: HistoryEntity) =
        viewModelScope.launch {
            repository.deleteRound(historyEntity)
        }

}