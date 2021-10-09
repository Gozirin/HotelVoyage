package com.example.hbapplicationgroupa.application

import android.app.Application
import com.example.hbapplicationgroupa.database.HotelDatabase
import com.example.hbapplicationgroupa.database.dao.AmenitiesDao
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAdminModuleApi
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAmenitiesModuleApi
import com.example.hbapplicationgroupa.repository.amenitiesDaoRepository.AmenitiesDaoRepository
import com.example.hbapplicationgroupa.repository.amenitiesDaoRepository.AmenitiesDaoRepositoryInterface
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@HiltAndroidApp
class HotelApplication : Application()
