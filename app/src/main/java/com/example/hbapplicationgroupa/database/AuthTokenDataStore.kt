package com.example.hbapplicationgroupa.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * This class is created to use data store preferences to store auth token generated when a user logs in
 * This auth token can be used to monitor user login sessions
 */

private val Context.dataStore by preferencesDataStore("user_preferences")
class AuthTokenDataStore(context: Context) {

    private val applicationContext = context.applicationContext

    //dataStore instance
    private val dataStore: DataStore<Preferences> = context.dataStore

    //creating key for authToken
    companion object{
        private val KEY_AUTH = stringPreferencesKey("key_auth")

        private val KEY_ID = stringPreferencesKey("key_id")
    }

    //Method to save authentication id using the key created above
    suspend fun saveAuthId(id: String){
        dataStore.edit { preferences -> //Mutable Preferences
            preferences[KEY_ID] = id
        }
    }

    //To retrieve the saved ID, coroutines flow is used
    val id: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_ID]
        }

    //Method to save authentication token using the key created above
    suspend fun saveAuthToken(authToken: String){
        dataStore.edit { preferences -> //Mutable Preferences
            preferences[KEY_AUTH] = authToken
        }
    }

    //To retrieve the saved authToken, coroutines flow is used
    val authToken: Flow<String?>
        get() = dataStore.data.map { preferences ->
            preferences[KEY_AUTH]
        }

}