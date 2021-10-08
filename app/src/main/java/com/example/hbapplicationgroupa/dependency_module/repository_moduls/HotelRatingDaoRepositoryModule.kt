package com.example.hbapplicationgroupa.dependency_module.repository_moduls

import com.example.hbapplicationgroupa.database.dao.HotelRatingsDao
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBAdminModuleApi
import com.example.hbapplicationgroupa.repository.hotelRatingDaoRepository.HotelRatingDaoRepository

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class HotelRatingDaoRepositoryModule {

    fun provideHotelRatingDaoRepository ( hbAdminModuleApi: HBAdminModuleApi, hotelRatingsDao: HotelRatingsDao): HotelRatingDaoRepository {
    return HotelRatingDaoRepository(hbAdminModuleApi, hotelRatingsDao)

    }
 }