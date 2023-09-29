package com.ry05k2ulv.sudokusolver.datastore

import androidx.datastore.core.DataStore
import com.ry05k2ulv.sudokusolver.model.DarkThemeConfig
import com.ry05k2ulv.sudokusolver.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SsPreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data.map {
        UserData(
            useDynamicColor = it.useDynamicColor,
            darkThemeConfig = when (it.darkThemeConfig) {
                null, UserPreferences.DarkThemeConfigProto.UNRECOGNIZED, UserPreferences.DarkThemeConfigProto.SYSTEM -> DarkThemeConfig.SYSTEM
                UserPreferences.DarkThemeConfigProto.LIGHT -> DarkThemeConfig.LIGHT
                UserPreferences.DarkThemeConfigProto.DARK -> DarkThemeConfig.DARK
            }
        )
    }

    suspend fun setUseDynamicColor(useDynamicColor: Boolean) {
        userPreferences.updateData {
            it.toBuilder().setUseDynamicColor(useDynamicColor).build()
        }
    }

    suspend fun setDarkThemeConfig(darkThemeConfig: DarkThemeConfig) {
        userPreferences.updateData {
            it.toBuilder().setDarkThemeConfig(
                when (darkThemeConfig) {
                    DarkThemeConfig.SYSTEM -> UserPreferences.DarkThemeConfigProto.SYSTEM
                    DarkThemeConfig.LIGHT -> UserPreferences.DarkThemeConfigProto.LIGHT
                    DarkThemeConfig.DARK -> UserPreferences.DarkThemeConfigProto.DARK
                }
            ).build()
        }
    }
}