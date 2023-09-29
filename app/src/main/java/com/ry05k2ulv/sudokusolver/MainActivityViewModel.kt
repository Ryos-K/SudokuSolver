package com.ry05k2ulv.sudokusolver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ry05k2ulv.sudokusolver.MainActivityUiState.*
import com.ry05k2ulv.sudokusolver.data.UserDataRepository
import com.ry05k2ulv.sudokusolver.model.UserData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: UserDataRepository
) : ViewModel() {
    val uiState: StateFlow<MainActivityUiState> = repository.userData
        .map { userData ->
            Success(userData)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = Loading
        )
}

sealed interface MainActivityUiState {
    object Loading : MainActivityUiState
    data class Success(val userData: UserData) : MainActivityUiState
}