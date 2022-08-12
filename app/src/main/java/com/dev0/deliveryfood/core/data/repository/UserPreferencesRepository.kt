package com.dev0.deliveryfood.core.data.repository

import android.util.Log
import androidx.compose.ui.graphics.Color
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import java.io.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

data class UserPreferences(
    val appTheme: String
)

sealed class Theme {
    companion object {
        const val PINK: String = "pink"
        val WATER: String = "water"
        const val DEFAULT: String = "default"
        const val BLACK: String = "black"
    }
}


class UserPreferencesRepository (private val dataStore: DataStore<Preferences>) {

    private val TAG: String = "UserPreferencesRepo"

    private object PreferencesKeys {
        val APPLICATION_THEME = stringPreferencesKey("app_theme")
    }

    val userPreferencesFlow: Flow<UserPreferences> = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                Log.e(TAG, "Error reading preferences.", exception)
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }.map { preferences ->
            mapUserPreferences(preferences)
        }

    suspend fun updateAppTheme(appTheme: String) {

        dataStore.edit { preferences ->
            var a = preferences[PreferencesKeys.APPLICATION_THEME];
            preferences[PreferencesKeys.APPLICATION_THEME] = appTheme

        }
    }

    private fun mapUserPreferences(preferences: Preferences): UserPreferences {

        val appTheme = preferences[PreferencesKeys.APPLICATION_THEME] ?: Theme.DEFAULT
        return UserPreferences(appTheme)
    }
}