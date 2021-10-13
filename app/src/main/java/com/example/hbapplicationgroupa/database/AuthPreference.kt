package com.example.hbapplicationgroupa.database

import android.app.Activity
import android.content.Context
import android.content.SharedPreferences

class AuthPreference(activity: Activity) {
    val MY_PREF = "my_pref"
    val TOKEN_KEY = "token_key"
    val ID_KEY = "id_key"

    val myPreference: SharedPreferences = activity.getSharedPreferences(MY_PREF, Context.MODE_PRIVATE)

    fun setToken(token: String){
        myPreference.edit().putString(TOKEN_KEY, token).apply()
    }

    fun setId(id: String){
        myPreference.edit().putString(ID_KEY, id).apply()
    }

    fun getToken(key: String) : String? {
        return myPreference.getString(key, null)
    }

    fun getId(key: String): String? {
        return myPreference.getString(key, null)
    }

    fun clear(key: String){
        myPreference.edit().remove(key).apply()
    }
}