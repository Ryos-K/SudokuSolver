package com.ry05k2ulv.sudokusolver.data

import com.ry05k2ulv.sudokusolver.datastore.SsPreferencesDataSource
import com.ry05k2ulv.sudokusolver.model.DarkThemeConfig
import com.ry05k2ulv.sudokusolver.model.UserData
import kotlinx.coroutines.flow.Flow

import javax.inject.Inject

class UserDataRepository @Inject constructor(
    private val ssPreferenceDataSource: SsPreferencesDataSource
) {
    val userData: Flow<UserData> = ssPreferenceDataSource.userData

    suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
        ssPreferenceDataSource.setUseDynamicColor(useDynamicColor)
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        ssPreferenceDataSource.setDarkThemeConfig(darkThemeConfig)
    }
}