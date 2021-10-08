package com.example.hbapplicationgroupa.dependency_module.repository_moduls

import com.example.hbapplicationgroupa.database.dao.HotelDao
import com.example.hbapplicationgroupa.network.hotelbookingapi.HBHotelModuleApi
import com.example.hbapplicationgroupa.repository.hotelDaoRepository.HotelDaoRepository
import com.example.hbapplicationgroupa.repository.hotelDaoRepository.HotelDaoRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class HotelDaoRepositoryModule {

    @Singleton
    @Provides
    fun provideHotelDaoRepository(hotelModuleApi: HBHotelModuleApi, hotelDao: HotelDao):HotelDaoRepository{
    return HotelDaoRepository(hotelModuleApi, hotelDao )
    }
}