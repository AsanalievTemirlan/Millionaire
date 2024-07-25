package com.example.millionaire.presentation.ui.fragment.game

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.millionaire.data.model.HistoryEntity
import com.example.millionaire.data.repository.MillionaireRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameViewModel @Inject constructor(private val repository: MillionaireRepository) :
    ViewModel() {

    fun insert(historyEntity: HistoryEntity) =
        viewModelScope.launch { repository.insertRound(historyEntity) }

}