package com.ry05k2ulv.sudokusolver.di

import android.content.Context
import androidx.core.view.WindowInsetsAnimationCompat.Callback.DispatchMode
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.dataStoreFile
import com.ry05k2ulv.sudokusolver.datastore.UserPreferences
import com.ry05k2ulv.sudokusolver.datastore.UserPreferencesSerializer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataStoreModule {
    @Provides
    @Singleton
    fun providesUserPreferencesDataStore(
        @ApplicationContext context: Context,
        userPreferencesSerializer: UserPreferencesSerializer
    ) : DataStore<UserPreferences> =
        DataStoreFactory.create(
            serializer = userPreferencesSerializer
        ) {
            context.dataStoreFile("user_preferences.pb")
        }
}