package com.ry05k2ulv.sudokusolver.ui.settings

import androidx.lifecycle.ViewModel
import com.ry05k2ulv.sudokusolver.model.DarkThemeConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(

) : ViewModel() {


    fun updateDarkTheme(darkThemeConfig: DarkThemeConfig) {

    }

    fun updateDynamicColor(useDynamicColor: Boolean) {

    }
}

data class UserSettings(
    val darkThemeConfig: DarkThemeConfig,
    val useDynamicColor: Boolean
)

sealed interface SettingsUiState {
    object Loading : SettingsUiState
    data class Success(val settings: UserSettings) : SettingsUiState
}