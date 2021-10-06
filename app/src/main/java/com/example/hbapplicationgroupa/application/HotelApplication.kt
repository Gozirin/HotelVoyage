package com.example.hbapplicationgroupa.application

import android.app.Application
import com.example.hbapplicationgroupa.database.HotelDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HotelApplication : Application() {



    companion object{
        private lateinit var instance:HotelApplication

        private val database : HotelDatabase by lazy {
            HotelDatabase.getDbInstance(instance)
        }
    }
}